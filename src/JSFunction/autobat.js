/**
 * скрипт работает по адрессу
 * http://x-grad.com/game/
 *
 *  id_mob(arg) - переменая определяющая на какого моба напасть
 *  min_xp(arg)  - минимум хп после которого начинается лечение
 *  end_loop(arg) - время работы в полусекундах, end_loop=0 остановит работу, любое положительное число запустит
 *  let log_time_run(arg) - показывать отчет времени до завершения
 *  let log_loot(arg) - показывать отчет по дропу
 *  start() - запусть отслеживатель
 *  stop() - остановить
 *  openBox(name) - вскрыть коробки доступные значения PATH_BOX_OBELISK, PATH_BOX_RAT и PATH_BOX_SPRING_4
 *  timerRunaway(time) - команда побега на кодаф, время через которое надо сбежать на кадаф пишется в секунда
 * */

(function () {
    'use strict';

    let ability = 0;
    let timer_auto = 0;

    let runaway_bool=false;


   class Settings{
        constructor(){
            this.id_mob = 0;
            this.min_xp = 50;
            this.end_loop = 999;
            this.log_time_run = false;
            this.log_loot = true;
            this.runaway_bool = true;
            this.traps_bool = true;
            this.knife_bool = true;
        }
    }

    let settings = new Settings();

    const PATH_BOX_OBELISK = "//*[contains(@alt,'Обелиск ')]/../../a";
    const PATH_BOX_SPRING_4 = "//*[contains(@alt,'Коробка майских праздников')]/../../a";
    const PATH_BOX_RAT = "//*[contains(@alt,'Крысиная коробка')]/../../a";
    const PATH_BTN_INFO = "//*[contains(@class,'btn-info')]";
    const PATH_TEXT_BOX_LOOT = "//*[@class='content']/img";
    const PATH_TEXT_MOB_LOOT = "//*[@class='reward_loot']/*/*";
    const PATH_BTN_BAG = "//*[contains(@class,'bag ')]";


    const PATH_OBJ_BTN_ATTACK_RED = "//*[contains(text(),'Атака')]/..";
    const PATH_OBJ_BTN_ATTACK_BLUE = "//strong[contains(text(),'Защита')]/..";
    const PATH_OBJ_BTN_CLOSE = "//*[contains(text(),'закрыть')]/../../a";
    const PATH_OBJ_BTN_ABILITY = "//a[contains(@class,'ability')]";
    const PATH_OBJ_PROGRESS_ALLIES = "//*[contains(@class,'warriors-list type1')]//*[contains(@class,'progress')] /*[contains(@style,'width')] ";
    const PATH_OBJ_PROGRESS_ENEMIES = "//*[contains(@class,'warriors-list type2')]//*[contains(@class,'progress')] /*[contains(@style,'width')] ";
    const PATH_OBJ_HEALING = "//*[contains(@title,'осстанавл')]";
    const PATH_OBJ_CROSS = "//*[contains(@title,'эрлийский крест')]";
    const PATH_OBJ_TRAP = "//*[contains(@title,'ловушка')]";
    const PATH_OBJ_KNIFE = "//*[contains(@title,'шкур')]";
    const PATH_OBJ_TIMER_MOB = "//*[contains(@class,'mob')]";
    const PATH_OBJ_BATTLE = "//a[contains(text(),'Бой')]";
    const PATH_OBJ_ATTACKING = "//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]";
    const PATH_OBJ_BTN_MAP = "//*[contains(@onclick,'map')]";
    const PATH_OBJ_MAP_KADAF = "//area[contains(@data-id,'11')]";
    const PATH_OBJ_BTN_PORTAL = "//*[contains(@class,'jqmClose')]";
    const PATH_OBJ_TEXT_PORTAL = "//*[contains(@class,'jqmClose')]/*[contains(text(),'Портал')]";
    const PATH_BTN_BATTLE_CLOSE ="//*[contains(@class,'battle btn-close')]";

    function checkElement(path) {
        let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let result = (elem.snapshotLength > 0);
        elem = null;
        return result;
    }

    function clickElements(path, id) {
        if (!checkElement(path)) return false;
        let elem = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        if (id > elem.snapshotLength) {
            id = elem.snapshotLength - 1;
        }
        elem.snapshotItem(id).click();
        elem = null;
        return true;
    }


    function printTimer() {
        if (settings.log_time_run) {
            let hour = end_loop;
            let mili = hour % 2;
            hour -= mili;
            hour /= 2;
            mili *= 5;
            let sec = hour % 60;
            hour -= sec;
            hour /= 60;
            let minute = hour % 60;
            hour -= minute;
            hour /= 60;
            console.log("" + hour + ":" + minute + ":" + sec + "." + mili);
        }
    }

    function printLoot() {
        let path = PATH_TEXT_MOB_LOOT;
        if (!checkElement(path)) return;
        let mes_loot = "Вы получили: ";
        let mes_loot_obj = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let id = mes_loot_obj.snapshotLength - 1;
        while (id > 0) {
            mes_loot += "\"" + mes_loot_obj.snapshotItem(id).title + "\", ";
            id--;
        }
        mes_loot += "\"" + mes_loot_obj.snapshotItem(id).title + "\". ";
        console.log(mes_loot);
        mes_loot_obj = null;
    }

    function printLootBox() {
        const path =PATH_TEXT_BOX_LOOT;
        let mes_loot = "Вы получили: ";
        let mes_loot_obj = document.evaluate(path, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let id = mes_loot_obj.snapshotLength - 1;
        if (id<0)
            return;
        while (id > 0) {
            mes_loot += "\"" + mes_loot_obj.snapshotItem(id).alt + "\", ";
            id--;
        }
        mes_loot += "\"" + mes_loot_obj.snapshotItem(id).alt + "\". ";
        console.log(mes_loot);
        mes_loot_obj = null;
    }

    function clickItem(name, amt) {
        if (amt<=0)return;
        checkElement(name,0);
        amt--;
        setTimeout(printLootBox,500,name,amt);
        setTimeout(clearMessageWindows,500);
        setTimeout(clickItem,500,name,amt);
    }

    function findBeg(name,amt,id_start) {
        let obj_beg = document.evaluate(PATH_BTN_BAG, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let bag_id = id_start;
        if (bag_id >= obj_beg.snapshotLength) {
            return;
        }
        obj_beg.snapshotItem(bag_id).click();
        obj_beg = null;
        if (checkElement(name))
            setTimeout(clickItem, 500, name, amt);
        else
            setTimeout(findBeg, 500, name, amt,bag_id + 1);
    }

    function openBox(name,amt) {
        if (!clickElements(PATH_BTN_INFO,0))
            return;
        setTimeout(findBeg,500,name,amt,0);
    }

    function clickAbility() {
        ability++;
        ability%=2;
        clickElements(PATH_OBJ_BTN_ABILITY,ability);
    }

    function healing(){
        let progress_obj_allies = document.evaluate(PATH_OBJ_PROGRESS_ALLIES, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        const add_xp = (checkElement(PATH_OBJ_CROSS))?15:0;
        if (progress_obj_allies.snapshotLength > 0) {
            let str_xp = progress_obj_allies.snapshotItem(0).style.width;
            let  xp = add_xp+ Number(str_xp.split("%")[0]);
            if (settings.min_xp > xp){
                clickElements(PATH_OBJ_HEALING,0);
            }
        }
        clickElements(PATH_OBJ_PROGRESS_ENEMIES,0);
        progress_obj_allies = null;
    }

    function clearMessageWindows() {
        let mes_win_obj = document.evaluate(PATH_OBJ_BTN_CLOSE, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let id = mes_win_obj.snapshotLength - 1;
        while (id >= 0) {
            mes_win_obj.snapshotItem(id).click();
            id--;
        }
        mes_win_obj = null;
    }

    function usedTrap() {
        if (!settings.traps_bool)
            return;
        clickElements(PATH_OBJ_TRAP,0);
    }

    function usedKnife() {
        if (!settings.knife_bool)
            return;
        clickElements(PATH_OBJ_KNIFE,0);
    }

    function goToKadaf() {
        if (!clickElements(PATH_OBJ_BTN_MAP)) {
            return;
        }
        setTimeout(function () {
            clickElements(PATH_OBJ_MAP_KADAF,0);
            setTimeout(function () {
                if(checkElement(PATH_OBJ_TEXT_PORTAL)){
                    clickElements(PATH_OBJ_BTN_PORTAL,1);
                }else {
                    clickElements(PATH_OBJ_BTN_PORTAL,0);
                }
                stop();
            }, 500);
        }, 500);
    }

    function selectMob() {
        let timer_mob_obj = document.evaluate(PATH_OBJ_TIMER_MOB,
            document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let timer_mob = 0;
        if (timer_mob_obj.snapshotLength > 0) {
            let timer_mob_arr = timer_mob_obj.snapshotItem(0).textContent.split(':');
            for (let j = 0; j < timer_mob_arr.length; j++) {
                timer_mob *= 60;
                timer_mob += timer_mob_arr[j];
            }
        }
        timer_mob_obj = null;
        if (timer_mob > 0) return;
        clickElements(PATH_OBJ_BATTLE, 0);
        clickElements(PATH_OBJ_ATTACKING, settings.id_mob);
    }

    function attack() {
        clickAbility();
        healing();
        usedTrap();
        usedKnife();
        clickElements(PATH_OBJ_BTN_ATTACK_RED, 0);
    }

    function start() { //запуск переодических действий
        stop();
        let flag = false;
        timer_auto = setInterval(function () {
            if (checkElement(PATH_BTN_BATTLE_CLOSE)) {
                attack();
                if (flag){
                    settings.end_loop--;
                    console.log(settings.end_loop);
                    flag = false;
                }
            } else {

                if (settings.end_loop <= 0) {
                    if (settings.runaway_bool) {
                        runaway_bool =settings.runaway_bool;
                    } else {
                        return;
                    }
                }
                if (runaway_bool) {
                    goToKadaf();
                    settings.end_loop = 0;
                    runaway_bool = false;
                    return;
                }
                selectMob();
                flag = true;
            }
            printTimer();
            if (settings.log_loot) {
                printLoot();
                clearMessageWindows();
            }else {
                clearMessageWindows();
            }
        }, 500);
    }

    function stop() {
        clearInterval(timer_auto);
    } //функция остановки полностью останавливает исполнение

    function timerRunaway(time){
        time*=1000;
        setTimeout(function () {
            runaway_bool = true;
            attack();
        },time);
    }

    start();

    //export
    window.start = start;
    window.stop = stop;
    window.settings = settings;
    window.openBox = openBox;
    window.PATH_BOX_OBELISK=PATH_BOX_OBELISK;
    window.PATH_BOX_SPRING_4=PATH_BOX_SPRING_4;
    window.PATH_BOX_RAT=PATH_BOX_RAT;	
    window.timerRunaway=timerRunaway;
})();

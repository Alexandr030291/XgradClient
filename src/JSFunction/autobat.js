/**
 * скрипт работает по адрессу
 * http://x-grad.com/game/
 *
 * id_mob - переменая определяющая на какого моба напасть
 * min_xp  - минимум хп после которого начинается лечение
 * end_loop - время работы в полусекундах, end_loop=0 остановит работу, любое положительное число запустит
 *  let log_time_run - показывать отчет времени до завершения
 *  let log_loot - показывать отчет по дропу
 *
 * */

(function () {
    'use strict';

    let id_mob = 0;
    let min_xp = 50;
    let end_loop = 8 * 60 * 60 * 2 - 2;
    let log_time_run = false;
    let log_loot = true;

    let ability = 0;
    let timer_auto = 0;

    const PATH_BOX_OBELISK = "//*[contains(@alt,'Обелиск ')]/../../a";
    const PATH_BTN_INFO ="//*[contains(@class,'btn-info')]";
    const PATH_TEXT_BOX_LOOT = "//*[@class='content']/img";
    const PATH_TEXT_MOB_LOOT = "//*[@class='reward_loot']/*/*";
    function findElements(arg) {
        let max_loop = 100;
        let result = [];
        let elem = document.evaluate(arg, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        let max_element = (elem.snapshotLength > max_loop) ? max_loop : elem.snapshotLength;
        for (let i = 0; i < max_element; i++) {
            result.push(elem.snapshotItem(i))
        }
        elem = null;
        return result;

    }

    function findObjBtnAttackRed(){return findElements("//*[contains(text(),'Атака')]/.."); }
    function findObjBtnAttackBlue(){return findElements("//*[contains(text(),'Защита')]/.."); }
    function findObjBtnClose(){return findElements("//*[contains(text(),'закрыть')]/../../a");}
    function findObjBtnAbility(){return findElements("//a[contains(@class,'ability')]");}
    function findObjProgressAllies(){return findElements("//*[contains(@class,'warriors-list type1')]//*[contains(@class,'progress')] /*[contains(@style,'width')] ");}
    function findObjProgressEnemies(){return findElements("//*[contains(@class,'warriors-list type2')]//*[contains(@class,'progress')] /*[contains(@style,'width')] ");}
    function findObjHealing(){ return findElements("//*[contains(@title,'осстанавл')]");}
    function findObjCross() {return findElements("//*[contains(@title,'эрлийский крест')]");}
    function findObjTrap(){return findElements("//*[contains(@title,'ловушка')]");}
    function findObjKnife(){return findElements("//*[contains(@title,'шкур')]")}
    function findObjTimerMob() {return findElements("//*[contains(@class,'mob')]");}
    function findObjBtnBattle(){return findElements("//a[contains(text(),'Бой')]");}
    function findObjBtnAttacking(){return findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]")}

    function printLoot(path) {
            let mes_loot = "Вы получили: ";
            let mes_loot_obj = findElements(path);
            for (let j = mes_loot_obj.length - 1; j > 0; j--) {
                mes_loot += "\""+mes_loot_obj[j].title + "\", ";
            }
            if (mes_loot_obj.length > 0) {
                mes_loot += "\""+mes_loot_obj[0].title + "\". ";
                console.log(mes_loot);
            }
            mes_loot_obj = null;
    }

    function printLootBox(path) {
        let mes_loot = "Вы получили: ";
        let mes_loot_obj = findElements(path);
        for (let j = mes_loot_obj.length - 1; j > 0; j--) {
            mes_loot += "\""+mes_loot_obj[j].alt + "\", ";
        }
        if (mes_loot_obj.length > 0) {
            mes_loot += "\""+mes_loot_obj[0].alt + "\". ";
            console.log(mes_loot);
        }
        mes_loot_obj = null;
    }


    function printTimer() {
        if (log_time_run) {
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

    function clearMessageWindows() {
        let mes_win_obj = findObjBtnClose();
        for (let j = mes_win_obj.length - 1; j >= 0; j--) {
            mes_win_obj[j].click();
        }
        mes_win_obj = null;
    }

    function clickAbility() {
        let btn_ability_obj = findObjBtnAbility();
        if (btn_ability_obj.length > 0) {
            btn_ability_obj[ability].click();
            ability++;
        }
        btn_ability_obj = null;
    }
    
    function healing() {
        let progress_obj_allies = findObjProgressAllies();
        let progress_obj_enemies = findObjProgressEnemies();
        let obj_healing = findObjHealing();
        let obj_cross = findObjCross();
        const add_xp = 15;
        if (progress_obj_allies.length > 0) {
            let str_xp = progress_obj_allies[0].style.width;
            let xp = str_xp.split("%")[0];
            if(obj_cross.length>0) xp+=add_xp;
            if (min_xp > xp
                && obj_healing.length > 0){
                obj_healing[0].click();
            }
        }
        if (progress_obj_enemies.length > 0) {
            progress_obj_enemies[0].click();
        }
        progress_obj_allies = null;
        progress_obj_enemies = null;
        obj_healing = null;
        obj_cross = null;
    }
    
    function usedTrap() {
        let obj_trap = findObjTrap();
        if (obj_trap.length > 0) {
            obj_trap[0].click();
        }
        obj_trap = null;
    }

    function usedKnife() {
        let obj_knife = findObjKnife();
        if (obj_knife.length > 0) {
            obj_knife[0].click();
        }
        obj_knife = null;
    }

    function selectMob() {
        let timer_mob_obj = findObjTimerMob();
        let timer_mob = 0;
        if (timer_mob_obj.length > 0) {
            let timer_mob_arr = timer_mob_obj[0].textContent.split(':');
            for (let j = 0; j < timer_mob_arr.length; j++) {
                timer_mob *= 60;
                timer_mob += timer_mob_arr[j];
            }
        }
        timer_mob_obj =null;
        if (timer_mob > 0) return;
        let obj_btn_battle = findObjBtnBattle();
        if (obj_btn_battle.length > 0) {
            obj_btn_battle[0].click();
            let obj_mob = findObjBtnAttacking();
            if (obj_mob.length > 0) {
                obj_mob [id_mob % obj_mob.length].click();
            }
        }
    }

    function attack() {
        id_mob %= 3;
        min_xp %= 100;
        ability %= 2;
        if (log_loot)
            printLoot(PATH_TEXT_MOB_LOOT);
        if (end_loop <= 0) {
            return;
        }
        end_loop--;
        let btn_attack_obj = findObjBtnAttackRed();
        let btn_attach_obj_blue = findObjBtnAttackBlue();
        if (btn_attack_obj.length > 0||btn_attach_obj_blue.length>0) {
            clickAbility();
            healing();
            usedTrap();
            usedKnife();
            btn_attack_obj=findObjBtnAttackRed();
            if (btn_attack_obj.length > 0) {
                btn_attack_obj[0].click()
            }
        } else {
            selectMob();
        }

        btn_attack_obj = null;
        btn_attach_obj_blue = null;
    }

    function start() { //запуск переодических действий
        timer_auto = setInterval(function () {
            attack();
            printTimer();
            clearMessageWindows();
        }, 500);
    }

    function stop() {
        clearInterval(timer_auto);
    } //функция остановки полностью останавливает исполнение

    function openBox(name,amt) {
        let obj_btn_info = findElements(PATH_BTN_INFO);
        if (obj_btn_info.length===0) return;
        obj_btn_info[0].click();
        let next = function () {
            let obj_box = findElements(name);
            console.log(amt);
            if (obj_box.length>0&&amt>0){
                obj_box[0].click();
            }else{
                return
            }
            setTimeout(printLootBox,250,PATH_TEXT_BOX_LOOT);
            setTimeout(clearMessageWindows,1000);
            obj_box = null;
            amt--;
            setTimeout(next,250);
        };
        setTimeout(next,500);
        obj_btn_info = null;
    }


    start();

    //export
    window.start = start;
    window.stop = stop;
    window.id_mob = id_mob;
    window.min_xp = min_xp;
    window.end_loop = end_loop ;
    window.log_time_run = log_time_run;
    window.log_loot = log_loot ;
    window.openBox = openBox;
    window.PATH_BOX_OBELISK=PATH_BOX_OBELISK;

})();

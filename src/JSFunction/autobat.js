/**
 * скрипт работает по адрессу
 * http://x-grad.com/game/
 * */

var id_mob=1; //переменая определяющая на какого моба напасть
var min_xp=50; //минимум хп после которого начинается лечение
var end_loop=60*30*2; //время работы в полусекундах, end_loop=0 остановит работу, любое положительное число запустит
var log_time_run =false; //показывать отчет времени до завершения

function findElements(arg){
    let max_loop = 100;
    let result = [];
    let elem = document.evaluate(arg, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    let max_element = (elem.snapshotLength>max_loop)?max_loop:elem.snapshotLength;
    for (let i = 0; i<max_element;i++){
        result.push(elem.snapshotItem(i))
    }
    return result
}

function attak(i){
    id_mob %= 3;
    min_xp%=100;
    i%=2;
    if(end_loop>0) {
        setTimeout(function () {
            attak(i);
            end_loop--;
            if(log_time_run)console.log(end_loop);
        }, 500);
    }else {
        setTimeout(function () {
            attak(i);
        }, 500);
        return;
    }
    if (findElements("//*[contains(text(),'закрыть')]/../../a").length>0){
        findElements("//*[contains(text(),'закрыть')]/../../a")[0].click();
    }
    if (findElements("//*[contains(text(),'Атака')]/..").length > 0) {
        if (findElements("//a[contains(@class,'ability')]").length > 0) {
            findElements("//a[contains(@class,'ability')]")[i].click();
            i++;
        }
        if (findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ").length > 0) {
            let str_xp =findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[0].style.width;
            let xp = str_xp.split("%")[0];
            findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[1].click();
            if (min_xp > xp
                && findElements("//*[contains(@title,'осстанавл')]").length > 0
                && findElements("//*[contains(@title,'эрлийский крест')]").length <= 1){
                findElements("//*[contains(@title,'осстанавл')]")[0].click();
            }
        }
        if (findElements("//*[contains(@title,'ловушка')]").length > 0){
            findElements("//*[contains(@title,'ловушка')]")[0].click();
        }else {
            if (findElements("//*[contains(text(),'Атака')]/..").length > 0) {
                findElements("//*[contains(text(),'Атака')]/..")[0].click()
            }
        }
    }else{
        let timer_mob_obj = findElements("//*[contains(@class,'mob')]");
        let timer_mob = 0;
        if (timer_mob_obj.length>0){
            let timer_mob_arr = timer_mob_obj[0].outerText.split(':');
            for (let j=0; j<timer_mob_arr.length;j++){
                timer_mob*=60;
                timer_mob+=timer_mob_arr[j];
            }
        }
        if (timer_mob>0) return;
        if (findElements("//a[contains(text(),'Бой')]").length>0){
            findElements("//a[contains(text(),'Бой')]")[0].click();
            let mob_max = findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]").length;
            if ( mob_max> 0
            /* && findElements("//a[contains(@class,'ability')]").length == 0*/) {
                findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]")[id_mob%mob_max].click();
            }
        }

    }
}

attak(0);


var id_mob=0;
var min_xp=20;
var end_loop=60;

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
    i%=2;
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
        if (findElements("//a[contains(text(),'Бой')]").length>0){
            findElements("//a[contains(text(),'Бой')]")[0].click();
            if (findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]").length > 0
            /* && findElements("//a[contains(@class,'ability')]").length == 0*/) {
                findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]")[id_mob].click();
            }
        }

    }
    if(end_loop>0) {
        setTimeout(function () {
            attak(i);
            end_loop--;
            console.log(end_loop);
        }, 500);
    }
}

attak(0);
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



function attak(i) {
    if (i > 1) i = 0;
    if (findElements("//*[contains(text(),'Атака')]/..").length > 0) {
        setTimeout(function () {
            if (findElements("//a[contains(@class,'ability')]").length > 0) {
                findElements("//a[contains(@class,'ability')]")[i].click();
                i++;
            }
            if (findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ").length > 0) {
                let str_xp =findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[0].style.width;
                let xp = str_xp.split("%")[0];
                findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[1].click();
                if (50 > xp && findElements("//*[contains(@title,'осстанавл')]").length > 0) {
                    findElements("//*[contains(@title,'осстанавл')]")[0].click();
                }
            }
            findElements("//*[contains(text(),'Атака')]/..")[0].click()
            setTimeout(attak (i), 500);
        },500);
    }
}

attak(0);
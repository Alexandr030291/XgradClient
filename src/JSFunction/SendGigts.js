
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

function send(users) {
    if (users.length > 0) {
        let user = users.pop();
        let timeout = 250;
        setTimeout(function () {
            findElements("//*[contains(@alt,'Коктейль')]/..")[0].click();
            setTimeout(function () {
                findElements("//*[contains(@class,'text target_user')]")[0].value = user;
                setTimeout(function () {
                    findElements("//*[contains(@value,'Submit')]")[0].click();
                    setTimeout(send(users), timeout * 4)
                }, timeout);
            }, timeout);
        }, timeout);
    }
}


send(["Nerefeld", "Eyvill Zapor", "Eyvill Pomidor", "gaijinko", "Legster", "LeizZ", "vbn m", "poiuyt", "varCAT", "Liposakcia"]);

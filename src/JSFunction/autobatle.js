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
//авто моб
function attakmob(id,kol) {
    let time_out =800;
    if (kol > 0) {
        kol--;
        if (findElements("//a[contains(text(),'Бой')]").length > 0) {
            findElements("//a[contains(text(),'Бой')]")[0].click();
            setTimeout(attak = function () {
                if (findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]").length > 0) {
                    findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]")[id].click();
                    let i_att=0;
                    let pop =1;
                    setTimeout(att = function(){
                        pop--;
                        if (pop>0||findElements("//*[contains(text(),'Атака')]/..").length > 0) {
                            pop=0;
                            setTimeout(function () {
                                if (findElements("//a[contains(@class,'ability')]").length > 0) {
                                    findElements("//a[contains(@class,'ability')]")[id].click();
                                    i_att++;
                                    i_att %= 2;
                                    setTimeout(function () {
                                        if (findElements("//*[contains(text(),'закрыть')]/../../a").length > 0) {
                                            findElements("//*[contains(text(),'закрыть')]/../../a")[0].click();
                                        }
                                    }, 500);
                                }
                                if (findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ").length > 0) {
                                    let str_xp =findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[0].style.width;
                                    let xp = str_xp.split("%")[0];
                                    findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[1].click();
                                    if (50 > xp && findElements("//*[contains(@title,'осстанавл')]").length > 0) {
                                        findElements("//*[contains(@title,'осстанавл')]")[0].click();
                                    }
                                }
                                if (findElements("//*[contains(text(),'Атака')]/..").length > 0) {
                                    findElements("//*[contains(text(),'Атака')]/..")[0].click();
                                    setTimeout(att(), 500);
                                }else {
                                    if (findElements("//*[contains(text(),'закрыть')]/../../a").length > 0) {
                                        findElements("//*[contains(text(),'закрыть')]/../../a")[0].click();
                                    }
                                    setTimeout(function () {
                                        if (pop = 0){
                                            let str_time = findElements("//*[contains(@class,'mob')] ")[0].textContent;
                                            let str_arr_time = str_time.split(":");
                                            let timeout = str_arr_time[0] * 60 + str_time;
                                            setTimeout(attakmob(id, kol), timeout * 1000);
                                            console.log(timeout*1000);
                                        }
                                        else {
                                            setTimeout(attak(), time_out);
                                        }
                                    },500);
                                }
                            },500);
                        }else {
                            setTimeout(function () {
                                if (pop = 0){
                                    let str_time = findElements("//*[contains(@class,'mob')] ")[0].textContent;
                                    let str_arr_time = str_time.split(":");
                                    let timeout = str_arr_time[0] * 60 + str_time;
                                    setTimeout(attakmob(id, kol), timeout * 1000);
                                    console.log(timeout*1000);
                                }
                                else {
                                    setTimeout(attak(), time_out);
                                }
                            },500);
                            setTimeout(attak(), time_out);
                        }
                    },time_out)
                }
            },time_out);
        }
    }
}

attakmob(0,5);


function attaks(i) {
    var xp_el = false;
    var xp_player = document.evaluate("//*[contains(@class,'warriors-list type1')]//*[contains(@style,'width')]", document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    if (xp_player.length > 0) {
        xp_player = xp_player.snapshotItem(0);
        xp_el = true;
    }
    setTimeout(attak = function (i) {
        if (i > 1) i = 0;
        if (findElements("//*[contains(text(),'Атака')]/..").length > 0) {
            setTimeout(function () {
                if (findElements("//a[contains(@class,'ability')]").length > 0) {
                    findElements("//a[contains(@class,'ability')]")[i].click();
                    i++;
                    if (findElements("//*[contains(text(),'закрыть')]/../../a").length > 0) {
                        findElements("//*[contains(text(),'закрыть')]/../../a")[0].click();
                    }
                }
                if (xp_el) {
                    let str_xp = xp_player.style.width;
                    let xp = str_xp.split("%")[0];
                    findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[1].click();
                    if (50 > xp && findElements("//*[contains(@title,'осстанавл')]").length > 0) {
                        findElements("//*[contains(@title,'осстанавл')]")[0].click();
                    }
                }
                findElements("//*[contains(text(),'Атака')]/..")[0].click();
                setTimeout(attak(i), 500);
            }, 500);
        }
        return findElements("//*[contains(text(),'Атака')]/..").length === 0;
    },500);
}
attaks(0);

/*
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

 */

var id_mob=0;
var min_xp=20;
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
                if (findElements("//*[contains(text(),'закрыть')]/../../a").length>0){
                    findElements("//*[contains(text(),'закрыть')]/../../a")[0].click();
                }
            }
            if (findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ").length > 0) {
                let str_xp =findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[0].style.width;
                let xp = str_xp.split("%")[0];
                findElements("//*[contains(@class,'progress')] /*[contains(@style,'width')] ")[1].click();
                if (min_xp > xp && findElements("//*[contains(@title,'осстанавл')]").length > 0) {
                    findElements("//*[contains(@title,'осстанавл')]")[0].click();
                }
            }if (findElements("//*[contains(text(),'Атака')]/..").length > 0) {
                findElements("//*[contains(text(),'Атака')]/..")[0].click()}//else{setTimeout(attak (i), 2000);}
            setTimeout(attak(i), 500);
        },500);
    }//else{setTimeout(attak(0), 2000);}
}

var timerId2 = setInterval(function () {id_mob%=3;


    if (findElements("//a[contains(text(),'Бой')]").length>0){
        findElements("//a[contains(text(),'Бой')]")[0].click();
        if (findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]").length > 0&&
            findElements("//a[contains(@class,'ability')]").length == 0) {
            //setTimeout(attak(0), 2000);
            findElements("//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]")[id_mob].click(); //id_mob++;//}else{attak (0);
        }
    }
},  5500);



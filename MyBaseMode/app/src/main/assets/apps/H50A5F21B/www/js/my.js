(function(){
window.my= {
//文本框游客信息数据转表格数据
strToObject:function(str) {
     //1.取行，2.去首尾空格，3.按规则取姓名、身份证、电话号码、备注
    var arr = str.split("\n");//取行
    var res = [];
    for (var i= 0;i<arr.length;i++){
        var v = arr[i];
        //v = $.trim(v);//去首尾空格
        v = v.replace(/^\s+|\s+$/g,'');
        if(v){
            //取姓名[规则：首次出现首尾为汉字，中间可以有空白]
            var name_map = /[\u2E80-\u9FFF]{1}[\u2E80-\u9FFF\s]*[\u2E80-\u9FFF]{1}/;
            var name_res = name_map.exec(v);//这里返回的数组 object类型
            var name = '';
            if(name_res){
                name = name_res[0];
            }else{
                lmui.toast("第"+(i+1)+"行姓名不能为空");
                return false;
            }
            //取身份证[规则：18位非零开头的数字]
            var card_map = /[1-9][0-9]{16}[0-9X]([^\d]|$)/;
            var card_res = card_map.exec(v);
            var card = '';
            if(card_res){
                card = card_res[0].substr(0,18);//取18位
                //检查身份证是否正确
                if(!my.IdentityCodeValid(card)){
										mui.toast('第'+(i+1)+'行身份证号码不正确')
                    return false;
                }
            }else{
                mui.toast("第"+(i+1)+"行身份证不能为空,或不够18位");
                return false;
            }
            //取电话号码[规则：11位1开头的数字]
            var tel_map = /1[1-9][0-9]{9}([^\dX]|$)/;
            var tel_res = tel_map.exec(v);
            var tel = '';
            if(tel_res){
                tel = tel_res[0].substr(0,11);//取11位
            }
            //取备注[规则：去掉前面所有匹配的字符，再首尾去空格]
            var mark = v;
            if(name) mark = mark.replace(name,'');
            if(card) mark = mark.replace(card,'');
            if(tel) mark = mark.replace(tel,'');
            mark = mark.replace(/^\s+|\s+$/g,'');//去首尾空格
            //mark必需有汉字、数字、字母以及下划线之一
            if(mark && !mark.match(/[\u2E80-\u9FFF]|\w/)) mark = '';

            var obj = my.getSexAge(card);
            res.push({name:name,sex:obj.sex,card:card,phone:tel,mark:mark,age:obj.age,man:obj.man});
        }
    }
    return res;
},

///身份证号码取性别、年龄
getSexAge:function(identityCard) {
   //处理18位的身份证号码从号码中得到生日和性别代码
    var strBirthday = identityCard.substr(6, 4) + "/" + identityCard.substr(10, 2) + "/" + identityCard.substr(12, 2);
    var strSex = identityCard.substr(16, 1);
    //时间字符串里，必须是“/”
    var birthDate = new Date(strBirthday);
    var nowDateTime = new Date();
    var age = nowDateTime.getFullYear() - birthDate.getFullYear();
    //再考虑月、天的因素;.getMonth()获取的是从0开始的，这里进行比较，不需要加1
    if (nowDateTime.getMonth() < birthDate.getMonth() || (nowDateTime.getMonth() == birthDate.getMonth() && nowDateTime.getDate() < birthDate.getDate())) {
        age--;
    }
    var man = (age<18) ? 0:1;
    //性别1男，2女
    var sex = (strSex % 2) == 0 ? 2:1;
    return {age:age,sex:sex,man:man};
},


///身份证号码验证
IdentityCodeValid:function (code) {
    var city = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江 ",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北 ",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏 ",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外"
    };
    var tip = "";
    var pass = true;

    if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
        tip = "身份证号格式错误";
        pass = false;
    }

    else if (!city[code.substr(0, 2)]) {
        tip = "地址编码错误";
        pass = false;
    }
    else {
        //18位身份证需要验证最后一位校验位
        if (code.length == 18) {
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
            //校验位
            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if (parity[sum % 11] != code[17]) {
                tip = "校验位错误";
                pass = false;
            }
        }
    }
    //if(!pass) alert(tip);
    return pass;
}
}
})();


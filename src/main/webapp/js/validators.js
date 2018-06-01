function validateMobile(mobNo) {
    var re = /^1\d{10}$/;
    if (re.test(mobNo)) {
	return true;
    } else {
	return false;
    }
}
function validatePassword(pass) {
    var re = /^[a-zA-Z0-9][a-zA-Z0-9.!@#$%^&*_]{5,17}$/;
    if (re.test(pass)) {
	return true;
    } else {
	return false;
    }
}
function validateHourMinute(timeStr){
    var re = /^(([01]?[0-9])|(2[0-3])):[0-5][0-9]$/;
    if (re.test(timeStr)) {
	return true;
    } else {
	return false;
    }
}
function validateDigits(str){
    var re = /^\d+$/;
    if (re.test(str)) {
	return true;
    } else {
	return false;
    }
}

    function isCardNo(card)
    {
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if(reg.test(card) === false)
        {
            // alert("身份证输入不合法");
            return false;
        }else {
            return true;
        }

    }
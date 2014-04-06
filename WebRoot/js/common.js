String.prototype.endWith = function (str) {
    if (str == null || str == "" || $.trim(str).length == 0
			|| $.trim(str).length > this.length) {
        return false;
    }
    var reg = new RegExp(str + "$");
    return reg.test(this);
}

String.prototype.startWith = function (str) {
    if (str == null || str == "" || str.trim().length == 0
			|| str.length > this.length) {
        return false;
    }
    var reg = new RegExp("^" + str);
    return reg.test(this);
}

String.prototype.trim = function () {
    return $.trim(this);
}

String.prototype.isNumber = function () {
    var pattern = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
    return pattern.test(this);
}

String.prototype.isDate = function () {
   !/Invalid|NaN/.test(new Date(this));
}

String.prototype.isDigit = function () {
    return /^\d+$/.test(this);
}



function isEmpty(elementId) {
	trim(elementId);
	var eleValue = $("#"+elementId).val();
	if (eleValue == "" || eleValue.length == 0) {
		
		$("#"+elementId).focus();
		$("#"+elementId).select();
		return true;
	}
	return false;
}


function isBiggerThan(elementId, count){
	trim(elementId);
	var eleValue = $("#"+elementId).val();
	if (eleValue == "" || eleValue.length < count) {
		$("#"+elementId).focus();
		$("#"+elementId).select();
		return true;
	}
	return false;
	
}


function isEmail(elementId) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (!pattern.test(eleValue)) {
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function isPostalcode(elementId, message) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^[1-9]\d{5}$/;
		if (!pattern.test(eleValue)) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}

function isPhone(elementId) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^(\d{3}\-)?(0\d{2,3}-)?[1-9]\d{6,7}$/;
		if (!pattern.test(eleValue)) {
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}

function isMobile(elementId) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^1[3,5,8]{1}[0-9]{1}[0-9]{8}$/;
		if (!pattern.test(eleValue)) {
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}

function isMobileCode(telcode, message) {
//    if (telcode != "") {
        var pattern = /^1[3,5,8]{1}[0-9]{1}[0-9]{8}$/;
        if (!pattern.test(telcode)) {
            alert(message);
//            $("#" + elementId).focus();
//            $("#" + elementId).select();
            return false;
        }
        return true;
//    }
//    return false;
}


function isEnglishLetter(elementId, message) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^[A-Za-z]+$/;
		if (!pattern.test(eleValue)) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function isEnglishChinese(elementId, message) {
	trim(elementId);
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^[A-Za-z\u4e00-\u9fa5 ]+$/;;
		if (!pattern.test(eleValue)) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function isNumber(elementId) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^[0-9]+$/;
		if (!pattern.test(eleValue)) {
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function isLetterOrNumber(elementId, message) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^[A-Za-z0-9]+$/;
		if (!pattern.test(eleValue)) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}

function isLetterOrNumberOrUnderline(elementId, message) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^\w+$/;
		if (!pattern.test(eleValue)) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function trim(elementId) {
	var str = $.trim($("#"+elementId).val());
	$("#"+elementId).val(str);
}


function clear(elementId) {
	$("#"+elementId).val("");
}
 

function closeContainer(elementId) {
	$("#"+elementId).hide();
}

function isCorrectExtName(elementId,extNameGroup,message) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var extName = eleValue.substring(eleValue.lastIndexOf('.') + 1, eleValue.length);
		var extNameArr = extNameGroup.split(',');
		var j = 0;
		for (var i=0;i<extNameArr.length;i++) {
			if (extName.toUpperCase() == extNameArr[i].toUpperCase()) {
				j++;
				return true;
			}
		}
		if (j == 0) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function compareDateRange(elementStart, elementMiddle, elementEnd, message) {
    var eleValueStart = $("#" + elementStart).val();
    var eleValueMiddle = $("#" + elementMiddle).val();
    var eleValueEnd = $("#" + elementEnd).val();
    if (eleValueStart != "" && eleValueMiddle != "" && eleValueEnd != "") {
        var dateStart = formatdate(eleValueStart, "-");
        var dateMiddle = formatdate(eleValueMiddle, "-");
        var dateEnd = formatdate(eleValueEnd, "-");
        if (dateMiddle > dateStart && dateMiddle < dateEnd) {
            return true;
        } else {
            alert(message);
        }
    }
    return false;
}

function compareNumberRange(elementStart, elementMiddle, elementEnd, message) {
    var eleValueStart = $("#" + elementStart).val();
    var eleValueMiddle = $("#" + elementMiddle).val();
    var eleValueEnd = $("#" + elementEnd).val();
    if (eleValueStart != "" && eleValueStart.isNumber()
         && eleValueMiddle != "" && eleValueMiddle.isNumber()
         && eleValueEnd != "" && eleValueEnd.isNumber()) {
        var numStart = new Number(eleValueStart);
        var numMiddle = new Number(eleValueMiddle);
        var numEnd = new Number(eleValueEnd);
        if (numMiddle >= numStart && numMiddle <= numEnd) {
            return true;
        } else {
            alert(message);
        }
    }
    return false;
}


function compareNumber(elementStart, elementEnd, message) {
    var eleValueStart = $("#" + elementStart).val();
    var eleValueEnd = $("#" + elementEnd).val();
    if (eleValueStart != "" && eleValueStart.isNumber() && eleValueEnd != "" && eleValueEnd.isNumber()) {
        var numStart = new Number(eleValueStart);
        var numEnd = new Number(eleValueEnd);
        if (numStart < numEnd) {
            alert(message);           
        } else {
            return true;
        }
    } 
    return false;
  
 }


function compareDate(elementStart, elementEnd, message) {
	var eleValueStart = $("#"+elementStart).val();
	var eleValueEnd = $("#"+elementEnd).val();
	if (eleValueStart != "" && eleValueEnd != "") {
 		var dateStart = formatdate(eleValueStart,"-");
 		var dateEnd = formatdate(eleValueEnd,"-");
		if(dateStart > dateEnd){
			alert(message);
		    return false;
	  	}
  	}
    return true;
}

function formatdate(strDate,strSep) {
	var dateArr = strDate.split(strSep);
    var forDate = new Date(dateArr[0],dateArr[1],dateArr[2]);
    return forDate;
}


function validateCharLength(elementId,characterL,message) {
	trim(elementId);
	var eleValue = $("#"+elementId).val();
	if (eleValue != "" && eleValue.length > characterL) {
		alert(message);
		$("#"+elementId).focus();
		$("#"+elementId).select();
		return false;
	}
	return true;
}


 function isCurrency(elementId, message) {
 	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^\d+(\.\d+)?$/;
		if (!(pattern.test(eleValue))) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		}
	}
	return true;
}


function isNumAndDecimal(elementId, digit, message) {
	var eleValue = $("#"+elementId).val();
	if (eleValue != "") {
		var pattern = /^\d+(\.\d+)?$/;
		if (!(pattern.test(eleValue))) {
			alert(message);
			$("#"+elementId).focus();
			$("#"+elementId).select();
			return false;
		} else if (eleValue.indexOf(".") != -1) {
			var decimalCount = eleValue.substring(eleValue.lastIndexOf('.') + 1, eleValue.length).length;
			if (decimalCount > digit) {
				alert(message);
				$("#"+elementId).focus();
				$("#"+elementId).select();
				return false;
			}
		}
	}
	return true;
}


function Map() {
    this.elements = new Array();
    this.size = function () {
        return this.elements.length;
    };
    this.put = function (_key, _value) {
        this.elements.push({ key: _key, value: _value });
    };
    this.remove = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        }
        catch (e) {
            bln = false;
        }
        return bln;
    };
    this.containsKey = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        }
        catch (e) {
            bln = false;
        }
        return bln;
    };
    this.get = function (_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        }
        catch (e) {
            return null;
        }
    };
}


function getObj(elementId) {
	return document.getElementById(elementId);
}

function isAllDigits(argvalue) {
	argvalue = argvalue.toString();
	var validChars = "0123456789";
	var startFrom = 0;
	for (var n = startFrom; n < argvalue.length; n++) {
		if (validChars.indexOf(argvalue.substring(n, n + 1)) == -1) {
			return false;
		}
	}
	return true;
}

function containIllegalChar(eleValue) {  
    var pattern = /[%']+/;
    return pattern.test(eleValue);
}


function startWithString(str, value) {
    var reg = new RegExp("^" + str);
    return reg.test(value);
}


function setCheckedAllFalse(strAllChoiceID, strChildID) {
    var elms = document.forms[0].elements;
    var vObj = document.getElementById(strAllChoiceID);
    var isCheckAll = true;
    if (vObj.type == "checkbox") {
        for (i = 0; i < elms.length; i++) {
            if (elms[i].type == "checkbox" && elms[i].id.indexOf(strChildID) >= 0) {
                if (!elms[i].checked && !elms[i].disabled) {
                    isCheckAll = false;
                    break;
                }
            }
        }
        vObj.checked = isCheckAll;
    }
    return false;
}


function selectAllCheckboxBoxes(spanChk, strChildSubID) {
    if (spanChk.type == "checkbox") {
        var xState = spanChk.checked;
        var elms = spanChk.form.elements;
        var i = 0;
        for (i = 0; i < elms.length; i++) {
            if (elms[i].type == "checkbox" && elms[i].id != spanChk.id && elms[i].id.indexOf(strChildSubID) >= 0) {
                if (!elms[i].disabled) {
                    if (elms[i].checked != xState) {
                        elms[i].click();
                        elms[i].checked = xState
                    }
                }
            }
        }
        spanChk.checked = xState;
        return true;
    } else {
        return false;
    }
}

function getActualLen(str) {
    var i;
    var len;
    len = 0;
    for (i = 0; i < str.trim().length; i++) {
        if (str.charCodeAt(i) > 255) len += 2; else len++;
    }
    return len;
}

function onKeyUpNumber(obj, digits) {
    var digit = digits;
    if (digits <= 0) {
        digit = 2;
    }   
    var srcValue = $telerik.$(obj).val();
    var realValue = srcValue.replace(/[^-\d\.]/g, "")
    var nIndex = srcValue.indexOf("-");
    var nLastIndex = srcValue.lastIndexOf("-");

    var index = srcValue.indexOf(".");
    var lastIndex = realValue.lastIndexOf(".");
    if (nIndex > 0 || index == 0 || nIndex != nLastIndex  || index != lastIndex) {
        $telerik.$(obj).val(0);
    } else {
        if (index >= 1 && index < realValue.length - 1 - digit) {
           
                index = index + 1 + digit;
                realValue = realValue.substring(0, index);
            
        }

        $telerik.$(obj).val(realValue);
    }

}

function onKeyUpPositiveNumber(obj, digits) {
    var digit = digits;
    if (digits <= 0) {
        digit = 2;
    }    
    var srcValue = $telerik.$(obj).val();  
    var realValue = srcValue.replace(/[^\d\.]/g, "")
    var index = realValue.indexOf(".");
    var lastIndex = realValue.lastIndexOf(".");
    if (index == 0 || index != lastIndex) {
        $telerik.$(obj).val(0);
    } else {
        if (index >= 1 && index < realValue.length - 1 - digit) {
           
                index = index + 1 + digit;
                realValue = realValue.substring(0, index);
           
            
            //else {
            //    var padNum = 1 + digit + index - realValue.length;
            //    var padStr = "";
            //    for (i = 0; i < padNum; i++) {
            //        padStr = padStr + "0";
            //    }
            //    realValue = realValue + padStr
           // }
        }

        $telerik.$(obj).val(realValue);
    }
}

function onKeyUpDigits(obj) {
    var srcValue = $telerik.$(obj).val();
    var realValue = srcValue.replace(/[^\d]/g, "")
    $telerik.$(obj).val(realValue);
}


function onKeyUpLetter(obj) {
    var srcValue = $telerik.$(obj).val();
    var realValue = srcValue.replace(/[^a-zA-Z]*/g, "")
    $telerik.$(obj).val(realValue);
}

function onKeyUpNDigits(obj) {
    var srcValue = $telerik.$(obj).val();
    var realValue = srcValue.replace(/[^-\d]/g, "")
    var nIndex = realValue.indexOf("-");
    if (nIndex > 0) {
        $telerik.$(obj).val(0);
    } else {
        $telerik.$(obj).val(realValue);
    }
}

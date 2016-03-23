   
/*用来验证是否是整数 alert(checkInt($("#nnnn").val())) */
function checkInt( s){ // 参数说明 re 为正则表达式 s 为要判断的字符
        var re = /^[0-9]*[1-9][0-9]*$/ ;
        return re.test(s)
    }

/* 是否是浮点数，包括整数 alert(checkfloat($("#nnnn").val())) */
    function checkfloat( s){ // 参数说明 re 为正则表达式 s 为要判断的字符
        var re=/^\d+(\.\d+)?$/ ;//非负浮点数（正浮点数 + 0）
        return re.test(s)
    }
    
    
/*    身份证正则表达式*/
    function checkCard( s){ // 参数说明 re 为正则表达式 s 为要判断的字符
    	var re = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
        return re.test(s)
    }
    
    /*   过滤非法字符*/
    function checkChar( s){  
    	var re = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]")
    	return re.test(s)
    }
   /* 密码区域以字母开头，长度在6-22之间*/
    function checkPwd( s){  
    	var re =/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
    	return re.test(s)
    }
    
    
    //判断日期类型是否为YYYY-MM-DD格式的类型,无法判断 13月 14月
    function isDate(str){

       // var str = new String(source);
        if(str.length>0){
            var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
            var r = str.match(reg);
            if(r==null)
            {
                return false
            }
            else{
                return true;
            }

        }
    }
      
    
    
    
  /*  把日期型撰文字符串 2014-01-02*/
    function ChangeDateToString ( DateIn )
    {
        var Year = 0 ;
        var Month = 0 ;
        var Day = 0 ;
        var CurrentDate = "" ;

        // 初始化时间
        Year = DateIn.getFullYear ();
        Month= DateIn.getMonth ()+ 1 ;
        Day= DateIn.getDate ();
        CurrentDate = Year + "-" ;
        if ( Month >= 10 )
        {
            CurrentDate = CurrentDate + Month + "-" ;
        }
        else
        {
            CurrentDate = CurrentDate + "0" + Month + "-" ;
        }

        if ( Day >= 10 )

        {

            CurrentDate = CurrentDate + Day ;
        }

        else

        {
            CurrentDate = CurrentDate + "0" + Day ;
        }

        return CurrentDate ;
    }

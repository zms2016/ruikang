/**
 * Created by Administrator on 2015/11/4.
 */
// 取得当前日期 , 格式 yyyy-mm-dd

function GetCurrentDate ()
{
    var year = 0 ;
    var Month = 0 ;
    var day = 0 ;
    var CurrentDate = new Date ();
    return ChangeDateToString ( CurrentDate );

}

// 取得当前日期 , 格式 yyyy-mm-dd hh:mm

function GetCurrentTime ()

{
    var Year = 0 ;
    var Month = 0 ;
    var Day = 0 ;
    var CurrentDate = new Date ();

    return ChangeTimeToString ( CurrentDate );

}

// 将日期类型转换成字符串型格式 yyyy-MM-dd

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



// 将日期类型转换成字符串型格式 yyyy



function ChangeDateYearToString ( DateIn )

{

    var Year = 0 ;

    var CurrentDate = "" ;



    Year = DateIn . getYear ();

    CurrentDate = Year ;

    return CurrentDate ;

}



// 将日期类型转换成字符串型格式 MM



function ChangeDateMonthToString ( DateIn )

{

    var Month = 0 ;

    var CurrentDate = "" ;

    Month = DateIn . getMonth ()+ 1 ;

    if ( Month >= 10 )

    {

        CurrentDate = CurrentDate + Month ;

    }

    else

    {

        CurrentDate = CurrentDate + "0" + Month ;

    }

    return CurrentDate ;

}



// 将日期类型转换成字符串型格式 yyyy-MM-dd hh:mm



function ChangeTimeToString ( DateIn )

{

    var Year = 0 ;

    var Month = 0 ;

    var Day = 0 ;

    var Hour = 0 ;

    var Minute = 0 ;

    var CurrentDate = "" ;



    // 初始化时间

    Year       = DateIn . getYear ();

    Month      = DateIn . getMonth ()+ 1 ;

    Day        = DateIn . getDate ();

    Hour       = DateIn . getHours ();

    Minute     = DateIn . getMinutes ();





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



    if ( Hour >= 10 )

    {

        CurrentDate = CurrentDate + " " + Hour ;

    }

    else

    {

        CurrentDate = CurrentDate + " 0" + Hour ;

    }

    if ( Minute >= 10 )

    {

        CurrentDate = CurrentDate + ":" + Minute ;

    }

    else

    {

        CurrentDate = CurrentDate + ":0" + Minute ;

    }

    return CurrentDate ;

}
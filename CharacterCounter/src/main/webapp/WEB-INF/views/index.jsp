<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script>
function fsubmit(obj){
    document.getElementById(obj).submit();
}


</script>

<html>
<head>
    <title>CharacterCounter</title>
</head>

<body>
<!--
}-->
<!--
    <form action="form_action.asp" method="post">
        <textarea rows="5" cols="100" autofocus="true" font-size="5">
        请输入文本……
        </textarea>

        <button type="button">统计</button>
        <button type="button">清空内容</button>
    </form>-->
    <form id="uploadForm" action="/home/receiveData" method="post">
        请输入文本内容：<br>
        <textarea id="textarea" name="textarea" rows="5" cols="100" autofocus="true">${words.str}</textarea>
        <br>

    </form>
    <button type="button" onClick="javascript:fsubmit('uploadForm');">统计</button>
    <button type="button" onClick="javascript:fsubmit('clearForm');">清空文本</button>

    <form id="clearForm" action="/home/clearData" method="post">
    </form>
    <h4>各统计内容的个数如下</h4>

    <table border="1">
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        <tr>
            <th>英文字母</th>
            <th>${words.engCnt}</th>
        </tr>
        <tr>
            <th>数字</th>
            <th>${words.numCnt}</th>
        </tr>
        <tr>
            <th>中文汉字</th>
            <th>${words.chsCnt}</th>
        </tr>
        <tr>
            <th>中英文标点符号</th>
            <th>${words.punCnt}</th>
        </tr>
    </table>


    <h4>文中出现频率最高的三个字是</h4>

    <table border="1">
        <tr>
            <th>名称</th>
            <th>个数</th>
        </tr>
        <tr>
            <th>${words.firstWord}</th>
            <th>${words.firstCnt}</th>
        </tr>
        <tr>
            <th>${words.secondWord}</th>
            <th>${words.secondCnt}</th>
        </tr>
        <tr>
            <th>${words.thirdWord}</th>
            <th>${words.thirdCnt}</th>
        </tr>

    </table>

</body>
</html>
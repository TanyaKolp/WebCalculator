<html>
<head>
    <title>Calculator</title>
</head>
<body>

<form name="calc" method="post"
      action="http://localhost:4567/formCalc">
    <p><b>Enter first number</b><br/>
        <input type="text" name="firstNumber" size="20"/>
    </p>
    <p><b>Enter sign of operation</b><br/>
        <input type="text" name="operation" size="20"/>
    </p><p><b>Enter second number</b><br/>
    <input type="text" name="secondNumber" size="20"/>
</p>
    <p><input type="submit" value="Count up"/>
        <input type="reset" value="Clear"/></p>
</form>
Result : ${result}
</body>
</html>
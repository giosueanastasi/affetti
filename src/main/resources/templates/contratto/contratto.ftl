<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"></meta>
    <title>Report</title>
    <style>
        body { font-family: Arial, sans-serif; }
        h1 { color: #007bff; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
    </style>
</head>
<body>
    <h1>Stampa del contratto: ${protocollo}</h1>
     <table>
        <tr>
            <th>Nome</th>
            <th>Valore</th>
        </tr>
        <#list dati as chiave, valore>
            <tr>
                <td>${chiave}</td>
                <td>${valore}</td>
            </tr>
        </#list>
    </table>
    
   
</body>
</html>
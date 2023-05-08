var express = require('express')
var app = express()
const port = 80

app.get('/', function(request, response) {
    /* 코드 부분*/
    response.send('hi');
})
 
app.listen(port, function() {
  console.log('서버 실행')
})




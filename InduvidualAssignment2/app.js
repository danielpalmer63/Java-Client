const http = require('http');

const hostname = '127.0.0.1';
const port = 3000;

var strData = '';

const server = http.createServer((req, res) =>{
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/plain');

  let {method, url} = req;

  const fs = require('fs');
  if (url === '/logdate')
  {
    var today = new Date();
    var date = (today.getMonth()+1)+'-'+today.getDate()+'-'+today.getFullYear();
    fs.appendFile('datelog.txt', date+'\n', err =>{
      if (err)
      {
        throw err;
      }
      console.log('Wrote date to datelog.txt');
    });
  }
  else if (url==='/dates')
  {
    fs.readFile('datelog.txt', (err, data) =>{
      if (err)
      {
        throw err;
      }
      strData = data.toString();
      console.log('Reading dates from datelog.txt');
    });
    res.write(strData);
  }
  else
  {
    res.statusCode = 404;
    console.log('Returning 404');
  }
  res.end();
});

server.listen(port, hostname, () =>{
  console.log(`Server up at ${hostname}:${port}`);
});

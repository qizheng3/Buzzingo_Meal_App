

var distance = require('google-distance');
distance.get(
  {
    origin: 'San Francisco, CA',
    destination: 'Los Angeles, CA',
    mode: 'bicycling',
    units: 'imperial'
  },
  function(err, data) {
    if (err) return console.log(err);
    console.log(data);
});
print distance;
cordova.define("cordova-plugin-custom-log.log",function(require,exports,module){
var exec = require('cordova/exec');
    module.exports = {
        show:function(){
        console.log('show: ');
        exec(function(){
            console.log('callback 1: ');
        },function(error){
            console.log(error);
        }, "MyLogger", "log","[_message, _title, _buttonLabel]");
    }
};
})
cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
     {
              "id": "cordova-plugin-custom-log.log",
              "file": "plugins/cordova-plugin-custom-log/Log.js",
              "pluginId": "cordova-plugin-custom-log",
              "merges": [
                "navigator.log"
             ],
      },

  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "cordova-plugin-custom-log": "1.0.0"
  };
});
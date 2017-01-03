'use strict';

angular
.module('EvShare')
.service('LocalStorage', ['$window', LocalStorage]);

function LocalStorage($window) {
	var storage = $window.localstorage();

	return {
		get: get,
		put: put,
		remove: remove,
		clear: clear
	};

	function get(key) {
		return storage.getItem(key);
	}

	function put(key, value) {
		return storage.setItem(key, value);
	}

	function remove(key) {
		return storage.removeItem(key);
	}

	function clear() {
		return storage.clear();
	}
}
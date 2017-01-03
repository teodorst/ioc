'use strict';

angular
.module('EvShare')
.service('SecurityService', ['$http', SecurityService]);

function SecurityService($http) {
	return {
		login: login
	}
	
	function login(credentials) {
		return $http.post()
	}
}
'use strict';

angular
.module('EvShare')
.service('SecurityService', [
	'$http',
	'LocalStorage',
	'Constants',
	'$state',
	SecurityService]);

function SecurityService($http, LocalStorage, Constants, $state) {
	return {
		login: login,
		register: register,
		logout: logout
	};
	
	function login(credentials) {
		return $http.post(Url.API + '/auth', credentials)
			.then(function (response) {
				LocalStorage.put(Constants.AUTH.TOKEN, response);
				$state.go('home');
			});
	}

	function register(credentials) {
		return $http.post(Url.API + '/user', credentials);
	}

	function logout() {
		return LocalStorage.remove(Constants.AUTH.TOKEN);
	}
}
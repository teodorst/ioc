'use strict';

angular
	.module('EvShare')
	.controller('SecurityController', [
		'SecurityService',
		'LocalStorage',
		'Constants',
		'$state',
		SecurityController]);

function SecurityController(SecurityService, LocalStorage, Constants, $state) {
	var vm = this;

	vm.user = undefined;
	vm.pass = undefined;

	vm.firstName = undefined;
	vm.lastName = undefined;
	vm.registerPass = undefined;
	vm.email = undefined;

	vm.login = login;
	vm.register = register;
	vm.isState = isState;

	function login() {
		 LocalStorage.put(Constants.AUTH.TOKEN,
			"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyYWRlbmlzLmlvbmVzY3VAZ21haWwuY29tIiwiY3JlYXRlZCI6MTQ4MzcxNDMwNjI2MSwiZXhwIjoxNDg0MzE5MTA2fQ.EuhSfnUnJKUGSrO3sKk-wp2BePC0nxJqn8uc10apXhTNLzWsdXme5SGDCwGZp7ouuBFZVLnTsMjmJKP020H2rQ");
		$state.go('home.show');
		// return SecurityService.login({email: vm.user, password: vm.pass})
		// 	.then(function (response) {
		// 		LocalStorage.put(Constants.AUTH.TOKEN, response);
		// 		$state.go('home');
		// 	});
	}

	function register() {
		return SecurityService.register({email: vm.email, firstName: vm.firstName,
			lastName: vm.lastName, password: vm.registerPass});
	}

	function isState(state) {
		return $state.includes(state);
	}
}
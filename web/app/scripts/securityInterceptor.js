'use strict';

angular
.module('EvShare')
.service('SecurityInterceptor', ['$injector', SecurityInterceptor]);

function SecurityInterceptor($injector) {
	return {
		request: request
	};

	function request(config) {
		var SecurityService = $injector.get('SecurityService', 'SecurityInterceptor');

		if (SecurityService.isAuthenticated()) {
			config.headers['Authorization'] = SecurityService.getToken();
		}

		return config;
	}
}
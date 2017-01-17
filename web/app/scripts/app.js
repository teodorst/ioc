'use strict';

angular
	.module('EvShare', [
		'ngRoute',
		'ngResource',
		'myApp.version',
		'ds.clock',
		'ui.router',
		'ngMaterial'
	])

	// .config(['$httpProvider',
	// 	function ($httpProvider) {
	// 		$httpProvider.defaults.headers.common.Accept = 'application/json';
	// 		$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
	// 	}])
	//
	// .config(['$httpProvider', function ($httpProvider) {
	// 	$httpProvider.interceptors.push('SecurityInterceptor');
	// }])

	.config(['$urlRouterProvider', function navInterceptorConfig($urlRouterProvider) {
		$urlRouterProvider
			.when('/login', [
				'$state',
				'$injector',
				function ($state, $injector) {
					var SecurityService = $injector.get('SecurityService');

					// if already in login state and trying to reach again `/login`
					// don't do a redirection because it will enter in an infinite loop
					if ($state.$current.name === 'login') {
						return true;
					}

					// if already authenticated and trying to reach `/login`
					if (SecurityService.isAuthenticated()) {
						return '/home/show';
					}

					return false;
				}
			])
			.otherwise(function () {
				return '/login';
			});
	}
	])

	.config([
		'$stateProvider',
		'$urlRouterProvider',
		function ($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('/home/show');

			$stateProvider
				.state('login', {
					url: '/login',
					templateUrl: '/templates/login.html',
					controller: 'SecurityController as vm'
				})
				.state('register', {
					url: '/register',
					templateUrl: '/templates/register.html',
					controller: 'SecurityController as vm'
				})
				.state('home', {
					url: '/home',
					views: {
						'': {
							templateUrl: '/templates/home.html',
							controller: 'EvShareController as vm'
						},
						'profile@home': {
							templateUrl: '/templates/profile.html'
						}
					}
				})
				.state('home.create', {
					url: '/event',
					templateUrl: '/templates/createEvent.html'
				})
				.state('home.show', {
					url: '/show',
					active: true,
					templateUrl: '/templates/showEvents.html'
				})
				.state('home.show.event', {
					url: '/:id',
					templateUrl: '/templates/event.html',
					controller: 'EventController as vm'
				})
				.state('home.invite', {
					url: '/invite',
					templateUrl: '/templates/inviteFriends.html'
				})
				.state('home.upload', {
					url: '/upload',
					templateUrl: '/templates/showEvents.html'
				})
				.state('home.upload.event', {
					url: '/:id',
					templateUrl: '/templates/uploadPhoto.html'
				})
			;
		}])

	.constant('Constants', {
		URL: {
			API: 'http://46.101.218.125:8080'
		},
		AUTH: {
			TOKEN: 'Authorization'
		}
	});


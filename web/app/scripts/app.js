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
	.config([
		'$stateProvider',
		'$urlRouterProvider',
		function ($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('/home/show');

			$stateProvider
				.state('home', {
					url: '/home',
					abstract: true,
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
					templateUrl: '/templates/login.html'
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
					templateUrl: '/templates/uploadPhoto.html'
				})
			;
		}])
	.constant('Constants', {
		URL: {
			API: 'http://46.101.218.125:8080'
		},
		AUTH: {
			TOKEN: 'Auth'
		}
	});


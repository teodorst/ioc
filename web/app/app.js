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
			// success login redirects to this state, so the application has to provide it's home url
			// $urlRouterProvider.when(AuthConstants.APP_HOME_STATE_REDIRECT, '/search');

			$urlRouterProvider.otherwise('/home/show');

			$stateProvider
				.state('home', {
					url: '/home',
					abstract: true,
					views: {
						'': {
							templateUrl: 'home.html',
							controller: 'EvShareController as vm'
						},
						'profile@home': {
							templateUrl: 'profile.html'
						}
					}
				})
				.state('home.create', {
					url: '/event',
					templateUrl: 'createEvent.html'
				})
				.state('home.show', {
					url: '/show',
					active: true,
					templateUrl: 'showEvents.html'
				})
				.state('home.show.event', {
					url: '/:id',
					templateUrl: 'event.html'
				})
				.state('home.invite', {
					url: '/invite',
					templateUrl: 'inviteFriends.html'
				})
				.state('home.upload', {
					url: '/upload',
					templateUrl: 'uploadPhoto.html'
				})
			;
		}])
	.constant('Url', {
		API: ''
	})


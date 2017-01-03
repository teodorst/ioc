'use strict';

angular
	.module('EvShare')
	.factory('EventResource', ['$resource', function ($resource) {
		return $resource('http://46.101.218.125:8080/events', {}, {
			'query': {
				method: 'GET',
				isArray: true,
				params: {
					page: '@page',
					pageSize: '@pageSize',
					sortBy: '@sortBy',
					sortOrder: '@sortOrder'
				}
			},
			'getEvent': {
				method: 'GET',
				url: 'http://46.101.218.125:8080/event/:id'
			},
			'updateEvent': {
				method: 'POST',
				url: '/event/:id'
			},
			'createEvent': {
				method: 'POST',
				url: 'http://46.101.218.125:8080/event'
			},
			'invite': {
				method: 'POST',
				url: 'http://46.101.218.125:8080/event/:id/invite'
			}
		});
	}]);
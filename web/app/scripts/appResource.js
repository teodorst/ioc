'use strict';

angular
	.module('EvShare')
	.factory('EventResource', ['$resource', 'Constants', function ($resource, Constants) {
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
				url: Constants.URL.API + '/event/:id'
			},
			'updateEvent': {
				method: 'POST',
				url: Constants.URL.API + '/event/:id'
			},
			'createEvent': {
				method: 'POST',
				url: Constants.URL.API + '/event'
			},
			'invite': {
				method: 'POST',
				url: Constants.URL.API + '/event/:id/invite'
			}
		});
	}]);
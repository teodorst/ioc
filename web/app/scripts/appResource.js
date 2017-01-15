'use strict';

angular
	.module('EvShare')
	.factory('EventResource', ['$resource', 'Constants', function ($resource, Constants) {
		return $resource('http://46.101.218.125:8080/events', {}, {
			'getEvents': {
				method: 'GET'
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
			'inviteFriends': {
				method: 'POST',
				url: Constants.URL.API + '/event/:eventId/invite'
			},
			'getUsers': {
				method: 'GET',
				url: Constants.URL.API + '/users'
			}
		});
	}]);
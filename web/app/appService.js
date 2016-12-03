'use strict';

angular
	.module('EvShare')
	.service('EventService', ['EventResource', function (EventResource) {
		return {
			createEvent: createEvent
		};

		function createEvent(event) {
			return EventResource.save({name: event.name, location: event.location, date: event.date}).$promise;
		}
	}]);

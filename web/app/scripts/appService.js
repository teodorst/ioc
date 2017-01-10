'use strict';

angular
	.module('EvShare')
	.service('EventService', ['EventResource', 'Constants', '$http', function (EventResource, Constants, $http) {
		return {
			createEvent: createEvent,
			getEvents: getEvents,
			getEvent: getEvent,
			updateEvent: updateEvent
		};

		function createEvent(event) {
			// return EventResource
			// 	.createEvent({name: event.name,
			// 		location: event.location,
			// 		date: event.date.toDateString()})
			// 	.$promise;

			return $http.post(Constants.URL.API + '/event', {name: event.name,
						location: event.location,
						date: event.date.toDateString()});
		}

		function getEvents() {
			return EventResource.query().$promise;
		}

		function getEvent(id) {
			return EventResource.getEvent({id: id}).$promise;
		}

		function updateEvent(id, event) {
			return EventResource.updateEvent({id: id}).$promise;
		}
	}]);

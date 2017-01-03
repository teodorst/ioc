'use strict';

angular
	.module('EvShare')
	.service('EventService', ['EventResource', function (EventResource) {
		return {
			createEvent: createEvent,
			getEvents: getEvents,
			getEvent: getEvent,
			updateEvent: updateEvent
		};

		function createEvent(event) {
			return EventResource
				.createEvent({name: event.name,
					location: event.location,
					date: event.date.toLocaleString()})
				.$promise;
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

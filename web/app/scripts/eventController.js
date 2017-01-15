'use strict';

angular
.module('EvShare')
.controller('EventController', [
	'EventService',
	'$state',
	EventController]);

function EventController(EventService, $state) {
	var vm = this;

	vm.event = {
		id: 1,
		name: 'Interval 100 Cluj',
		location: 'Form Space Cluj-Napoca',
		date: new Date()
	};

	vm.go = go;
	
	//_init();
	
	function _init() {
		EventService.getEvent($state.params.id)
			.then(function (event) {
				vm.event = event;
			});
	}

	function go(state) {
		$state.go(state);
	}
}
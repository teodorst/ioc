'use strict';

angular
	.module('EvShare')
	.controller('EvShareController', [
		'$state',
		'$stateParams',
		'EventService',
		EvShareController]);

function EvShareController($state, $stateParams, EventService) {
	var vm = this;

	vm.go = go;
	vm.isState = isState;

	// event
	vm.event = {};
	vm.createEvent = createEvent;
	vm.events = [{
		id: 1,
		name: 'Interval'
	}];
	vm.selected = undefined;


	_init();
	function _init() {


	}

	function go(state) {
		$state.go(state);
	}

	function isState(state) {
		return $state.includes(state);
	}

	function createEvent() {
		EventService.createEvent(vm.event)
			.then(function (id) {
				vm.event.id = id;
			})
			.catch(function () {

			});

		$state.go('home');
	}
}

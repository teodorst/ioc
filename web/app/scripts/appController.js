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
		name: 'Interval 100 Cluj'
	},
		{
			id: 2,
			name: 'Interval 100'
		},
		{
			id: 3,
			name: 'Interval Natural'
		}];
	vm.selected = undefined;

	vm.isInViewState = isInViewState;

	function go(state) {
		$state.go(state);
	}

	function isState(state) {
		return $state.includes(state);
	}

	function createEvent() {
		EventService.createEvent(vm.event)
			.then(function () {
			});

		$state.go('home');
	}

	function isInViewState() {
		return $state.params.id === undefined;
	}
}

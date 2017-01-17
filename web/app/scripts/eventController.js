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

	vm.users = [{firstName: 'Andra', lastName: 'Ionescu', email: 'andra@gmail.com'},
		{firstName: 'Teodor', lastName: 'Stefu', email: 'tedi@gmail.com'},
		{firstName: 'Vlad', lastName: 'Postoaca', email: 'vlad@gmail.com'},
		{firstName: 'Andra', lastName: 'Ionescu', email: 'andra@gmail.com'},
		{firstName: 'Teodor', lastName: 'Stefu', email: 'tedi@gmail.com'}];
	vm.allContacts = _loadContacts();

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

	function _loadContacts() {
		return vm.users.map(function (c) {
			c.name = c.firstName + ' ' + c.lastName;
			c._lowername = c.firstName.toLowerCase() + ' ' + c.lastName.toLowerCase();
			return c;
		});
	}
}
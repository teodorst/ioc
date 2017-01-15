'use strict';

angular
	.module('EvShare')
	.controller('CreateEventController', [CreateEventController]);

function CreateEventController() {
	var vm = this;

	vm.title = 'CreateEventController';
	vm.onCreate = true;


}
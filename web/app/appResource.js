'use strict';

angular
	.module('EvShare')
	.factory('EventResource', ['$resource', function ($resource) {
		return $resource('/event');
	}]);
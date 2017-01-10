'use strict';

angular
.module('EvShare')
.factory('SecurityResource', [
	'$resource',
	'Constants',
	SecurityResource]);

function SecurityResource($resource, Constants) {
	return $resource(Constants.URL.API + '/auth', {}, {
		'authenticate': {
			method: 'POST'
		},
		'register': {
			method: 'POST',
			url: Constants.URL.API + '/user'
		}
	});


}
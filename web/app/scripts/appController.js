'use strict';

angular
	.module('EvShare')
	.controller('EvShareController', [
		'$state',
		'$stateParams',
		'EventService',
		'$mdSidenav',
		'LocalStorage',
		'Constants',
		'$q',
		'$timeout',
		EvShareController]);

function EvShareController($state, $stateParams, EventService, $mdSidenav, LocalStorage, Constants, $q, $timeout) {
	var vm = this;

	vm.go = go;
	vm.isState = isState;
	vm.createEvent = createEvent;
	vm.openLeftMenu = openLeftMenu;
	vm.isInViewState = isInViewState;

	// event
	vm.selected = undefined;
	vm.event = {};
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

	// invite friends
	vm.selectedUser = undefined;
	vm.user = undefined;
	vm.users = [{firstName: 'Andra', lastName: 'Ionescu', email: 'andra@gmail.com'},
		{firstName: 'Teodor', lastName: 'Stefu', email: 'tedi@gmail.com'},
		{firstName: 'Adrian', lastName: 'Tanase', email: 'adi@gmail.com'},
		{firstName: 'Vlad', lastName: 'Postoaca', email: 'vlad@gmail.com'}];
	vm.loadContacts = loadContacts;

	//_init();

	function _init() {
		// LocalStorage.remove(Constants.AUTH.TOKEN);

	}

	function go(state) {
		$state.go(state);
	}

	function isState(state) {
		return $state.includes(state);
	}

	function createEvent() {
		EventService.createEvent(vm.event)
			.then(function () {
				// $state.go('home.show');
			});

		$state.go('home.show');
	}

	function isInViewState() {
		return $state.params.id === undefined;
	}

	function openLeftMenu() {
		$mdSidenav('left').toggle();
	}


	var pendingSearch;
	var lastSearch;

	vm.allContacts = loadContacts();
	vm.contacts = [vm.allContacts[0]];
	vm.asyncContacts = [];

	vm.delayedQuerySearch = delayedQuerySearch;

	/**
	 * Search for contacts; use a random delay to simulate a remote call
	 */
	function querySearch(criteria) {
		return vm.allContacts.filter(createFilterFor(criteria));
	}

	/**
	 * Async search for contacts
	 * Also debounce the queries; since the md-contact-chips does not support this
	 */
	function delayedQuerySearch(criteria) {
		if (!pendingSearch || !debounceSearch()) {
			return pendingSearch = $q(function (resolve) {
				$timeout(function () {
					resolve(querySearch(criteria));
					refreshDebounce();
				}, Math.random() * 500, true)
			});
		}

		return pendingSearch;
	}

	function refreshDebounce() {
		lastSearch = 0;
		pendingSearch = null;
	}

	/**
	 * Debounce if querying faster than 300ms
	 */
	function debounceSearch() {
		var now = new Date().getMilliseconds();
		lastSearch = lastSearch || now;

		return ((now - lastSearch) < 300);
	}

	/**
	 * Create filter function for a query string
	 */
	function createFilterFor(query) {
		var lowercaseQuery = angular.lowercase(query);

		return function filterFn(contact) {
			return (contact._lowername.indexOf(lowercaseQuery) != -1);
		};

	}

	function loadContacts() {
		return vm.users.map(function (c) {
			c.name = c.firstName + ' ' + c.lastName;
			c._lowername = c.firstName.toLowerCase() + ' ' + c.lastName.toLowerCase();
			return c;
		});
	}


	/**
	 Copyright 2016 Google Inc. All Rights Reserved.
	 Use of this source code is governed by an MIT-style license that can be foundin the LICENSE file at http://material.angularjs.org/HEAD/license.
	 **/

	// (function () {
	// 	'use strict';
	// 	angular
	// 		.module('autocompleteDemo', ['ngMaterial'])
	// 		.controller('DemoCtrl', DemoCtrl);
	//
	// 	function DemoCtrl ($timeout, $q, $log) {
	// 		var vm = this;
	//
	// 		self.simulateQuery = false;
	// 		self.isDisabled    = false;
	//
	// 		// list of `state` value/display objects
	// 		self.states        = loadAll();
	// 		self.querySearch   = querySearch;
	//
	// 		function querySearch (query) {
	// 			var results = query ? self.states.filter( createFilterFor(query) ) : self.states,
	// 				deferred;
	//
	// 				deferred = $q.defer();
	// 				$timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
	// 				return deferred.promise;
	//
	// 		}
	//
	// 		function loadAll() {
	// 			var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
	//          Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
	//          Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
	//          Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
	//          North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
	//          South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
	//          Wisconsin, Wyoming';
	//
	// 			return allStates.split(/, +/g).map( function (state) {
	// 				return {
	// 					value: state.toLowerCase(),
	// 					display: state
	// 				};
	// 			});
	// 		}
	//
	// 		function createFilterFor(query) {
	// 			var lowercaseQuery = angular.lowercase(query);
	//
	// 			return function filterFn(state) {
	// 				return (state.value.indexOf(lowercaseQuery) === 0);
	// 			};
	//
	// 		}
	// 	}
	// })();


}

angular.module('EvShare')
	.directive('chooseFile', function () {
		return {
			link: function (scope, elem, attrs) {
				var uploadButton = angular.element(elem[0].querySelector('#uploadButton'));
				// var deleteButton = angular.element(elem[0].querySelector('#deleteButton'));
				var submitButton = angular.element(elem[0].querySelector('#submitButton'));

				var input = angular.element(elem[0].querySelector('input#fileInput'));

				uploadButton.on('click', function () {
					input[0].click();
				});

				// deleteButton.on('click', function () {
				// 	scope.$apply(function () {
				// 		scope.vm.fileName = "";
				// 	});
				// });

				submitButton.on('click', function () {
					scope.vm.upload();
				});

				input.bind('change', function (e) {
					scope.$apply(function () {
						var files = e.target.files;
						if (files[0]) {
							scope.vm.fileName = files[0].name;
						} else {
							scope.vm.fileName = null;
						}
					});
				});
			}
		};
	})
;
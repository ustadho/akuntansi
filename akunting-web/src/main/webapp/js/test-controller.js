/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var myAppModule = angular.module('myApp', []);
myAppModule.controller('StartUpController', function($scope) {
    var students = [{name: 'Mary Contrary', id: '1'},
        {name: 'Jack Sprat', id: '2'},
        {name: 'Jill Hill', id: '3'}];

    $scope.funding = {startingEstimate: 0};
    $scope.computeNeeded = function() {
        $scope.needed = $scope.funding.startingEstimate * 10;
    };

    $scope.submitTest = function() {
        window.alert('Sory, please get more customers first!');
    };


});

myAppModule.controller('StudentListController', function($scope) {
    var students = [{name: 'Mary Contrary', id: '1'},
        {name: 'Jack Sprat', id: '2'},
        {name: 'Jill Hill', id: '3'}];
    $scope.students = students;
    $scope.insertTom = function() {
        $scope.students.splice(2, 0, {name: 'Tom Thumb', id: '4'});
    }
    
});

myAppModule.controller('CartController', function($scope){
   $scope.bill={};
   $scope.items = [
       {barang: 'Jeruk', qty: 2, harga: 1000},
       {barang: 'Apel', qty: 5, harga: 1600},
       {barang: 'Mangga', qty: 10, harga: 1200},
       {barang: 'Nangka', qty: 1, harga: 15000},
   ];
   
   var calculateTotal = function(){
       var total=0;
       for(var i=0; i<$scope.items.length; i++){
           total = total + $scope.items[i].qty * $scope.items[i].harga;
       }
       $scope.bill.totalCart= total;
       $scope.bill.disc= total > 10000? 10: 0;
       $scope.bill.subTotal=total - $scope.bill.disc;
   };
   
   $scope.$watch('items', calculateTotal, true);
});
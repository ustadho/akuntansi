angular.module('akunting.service', [ 'ngResource'])
    .factory('ApplicationConfigService', ['$resource', '$http', function($resource, $http){
        var service = {
            applicationConfig: $resource('config/:configId'),
            get: function(param, callback){ return this.applicationConfig.get(param, callback) }, 
            query: function(){ return this.applicationConfig.query() },
            save: function(obj){
                if(obj.id == null){
                    return $http.post('config', obj);
                } else {
                    return $http.put('config/'+obj.id, obj);
                }
            }, 
            remove: function(obj){
                if(obj.id != null){
                    return $http.delete('config/'+obj.id);
                }
            }
        };
            
        return service;
    }])
    .factory('ApplicationSessionsService', ['$http', function($http){
        var service = {
            list: function(){ 
                return $http.get('homepage/sessioninfo');
            }, 
            kick: function(user){
                return $http.delete('homepage/kick/'+user.sessionid);
            }
        };
            
        return service;
    }])
    .factory('SystemMenuService', ['$resource', '$http', function($resource, $http){
        var service = {
            menu: $resource('menu/:id', {}, {
                queryPage: {method:'GET', isArray: false}
            }),
            get: function(param, callback){ return this.menu.get(param, callback) }, 
            query: function(p, callback){ return this.menu.queryPage({"page.page": p, "page.size": 10}, callback) },
            save: function(obj){
                if(obj.id == null){
                    return $http.post('menu', obj);
                } else {
                    return $http.put('menu/'+obj.id, obj);
                }
            }, 
            remove: function(obj){
                if(obj.id != null){
                    return $http.delete('menu/'+obj.id);
                }
            }
        };
            
        return service;
    }])
    .factory('PermissionService', ['$resource', '$http', function($resource, $http){
        var service = {
            permission: $resource('permission/:id'),
            get: function(param, callback){ return this.permission.get(param, callback) }, 
            query: function(){ return this.permission.query() },
            save: function(obj){
                if(obj.id == null){
                    return $http.post('permission', obj);
                } else {
                    return $http.put('permission/'+obj.id, obj);
                }
            }, 
            remove: function(obj){
                if(obj.id != null){
                    return $http.delete('permission/'+obj.id);
                }
            }
        };
            
        return service;
    }])
    .factory('RoleService', ['$resource', '$http', function($resource, $http){
        var service = {
            role: $resource('role/:id'),
            get: function(param, callback){ return this.role.get(param, callback) }, 
            query: function(){ return this.role.query() },
            save: function(obj){
                if(obj.id == null){
                    return $http.post('role', obj);
                } else {
                    return $http.put('role/'+obj.id, obj);
                }
            }, 
            remove: function(obj){
                if(obj.id != null){
                    return $http.delete('role/'+obj.id);
                }
            },
            unselectedPermission: function(obj){
                return $http.get('role/'+obj.id+'/unselected-permission');
            },
            unselectedMenu: function(obj){
                return $http.get('role/'+obj.id+'/unselected-menu');
            }
        };
            
        return service;
    }])
    .factory('UserService', ['$resource', '$http', function($resource, $http){
        var service = {
            user: $resource('user/:id'),
            get: function(param, callback){ return this.user.get(param, callback) }, 
            query: function(){ return this.user.query() },
            save: function(obj){
                console.log(obj);
                if(obj.id == null){
                    console.log(obj);
                    return $http.post('user', obj);
                } else {
                    return $http.put('user/'+obj.id, obj);
                }
            }, 
            remove: function(obj){
                if(obj.id != null){
                    return $http.delete('user/'+obj.id);
                }
            }
        };
            
        return service;
    }])
    .factory('AccTypeService', ['$resource', '$http', function($resource, $http){
        var service = {
            coaType: $resource('acc-type/:typeId'),
            get: function(param, callback){ 
                return this.coaType.get(param, callback) 
            }, 
            query: function(){ return this.coaType.query() },
            save: function(obj){
                if(obj.typeId == null){
                    return $http.post('acc-type', obj);
                } else {
                    return $http.put('acc-type/'+obj.typeId, obj);
                }
            }, 
            remove: function(obj){
                if(obj.typeId != null){
                    return $http.delete('acc-type/'+obj.typeId);
                }
            },
            jurnalDetail : function (obj){
                return $http.get('jurnal-detail/'+obj.id);
            }       
        };
            
        return service;
    }])
    .factory('OfficeService', ['$resource', '$http', function($resource, $http){
            var service ={
                office: $resource('office/:id'),
                get: function(param, callback){return this.office.get(param, callback)},
                query: function(){return this.office.query()},
                save: function(obj){
                    if(obj.id==nul){
                        return $http.post('office', obj);
                    }else{
                        return $http.put('office/'+obj.id, obj);
                    }
                },
                remove: function(obj){
                    if(obj.id !=null){
                        return $http.delete('office/'+obj.id);
                    }
                }
            };
            return service;
    }])
    .factory('CurrencyService', ['$resource', '$http', function($resource, $http){
            var service = {
                curr: $resource('currency/:id'),
                get: function(param, callback){return this.curr.get(param, callback)},
                query: function(){return this.curr.query()},
                save : function(obj){
                    if(obj.id ==null){
                        return $http.post('currency', obj);
                    }else{
                        return $http.post('currency/'+obj.id, obj);
                    }
                    
                },
                remove: function(obj){
                    if(obj.id !=null){
                        return $http.delete('currency/'+obj.id);
                    }
                }
            };
            return service;
    }])
    .factory('CoaService', ['$resource', '$http', 'UserService', function($resource, $http, UserService){
        $http.get('homepage/userinfo').success(function(data) {
            $scope.userinfo = data;
        });
        var service = {
            coa: $resource('coa/:search', {}, {
                queryPage: {method:'GET', isArray: false}
            }),
            get: function(accNo, callback){
                console.log(accNo);
                //return this.coa.get(param.accNo+'/edit', callback) }, 
                return $http.get('coa/'+accNo+'/edit') ;},
            query: function(search, p, callback){ 
                return this.coa.queryPage(
                {"search":search, "page.page": p, "page.size": 10}, callback) },
            save: function(obj, oldAccNo){
                obj.user.id=$scope.userinfo.id;
                if(oldAccNo == null ){
                    return $http.post('coa', obj);
                } else {
                    return $http.put('coa/'+oldAccNo, obj);
                }
            }, 
            remove: function(obj){
                if(obj.accNo != null){
                    return $http.delete('coa/'+obj.accNo);
                }
            }, 
            listAll: function(){
                return $http.get('coa/all');
            },
            cariSatu: function(acc){
                return $http.get('coa/find/'+acc);
                
            }

        };
        return service;
    }])
    .factory('JurnalService', ['$resource', '$http', function($resource, $http){
        var service = {
            jurnal: $resource('/jurnal/:id/:mulai/:sampai', {}, {
                queryPage: {method:'GET', isArray: false},
            }),
            get: function(param, callback){ return this.jurnal.get(param, callback) }, 
            query: function(tanggal, p, callback){ 
                return this.jurnal.queryPage({"mulai":tanggal.mulai, "sampai":tanggal.sampai, 
                    "page.page": p, "page.size": 10}, callback) 
            },
            save: function(obj){
                console.log('Simpan jurnal :');
                console.log(obj);
                if(obj.id == null ){
                    return $http.post('jurnal', obj);
                } else {
                    return $http.put('jurnal/'+obj.id, obj);
                }
            }, 
            remove: function(obj){
                if(obj.accNo != null){
                    return $http.delete('jurnal/'+obj.id);
                }
            }
        };
        return service;
    }])
;
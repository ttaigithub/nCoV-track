import request from '../ajax/server'

export function queryRole(params) {
  return request.post('/role/query', params)
}

export function queryRoleAll(params) {
  return request.post('/role/list', params)
}

export function addRole(params) {
  return request.post('/role/add', params)
}

export function updateRole(params) {
  return request.post('/role/update', params)
}

export function deleteRole(params) {
  return request.post('/role/delete', params)
}

export function queryRolePermission(params) {
  return request.post('/role/permission', params)
}

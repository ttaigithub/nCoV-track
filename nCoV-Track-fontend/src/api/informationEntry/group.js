import request from '../ajax/server'

export function queryGroup(params) {
  return request.post('/group/query', params)
}

export function queryGroupAll(params) {
  return request.post('/group/groupList', params)
}

export function addGroup(params) {
  return request.post('/group/add', params)
}

export function updateGroup(params) {
  return request.post('/group/update', params)
}

export function deleteGroup(params) {
  return request.post('/group/delete', params)
}


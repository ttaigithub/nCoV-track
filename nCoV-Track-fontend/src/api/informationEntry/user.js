import request from '../ajax/server'

export function queryUser(params) {
  return request.post('/user/query', params)
}

export function queryUserAll(params) {
  return request.post('/user/userList', params)
}

export function addUser(params) {
  return request.post('/user/add', params)
}

export function updateUser(params) {
  return request.post('/user/update', params)
}

export function deleteUser(params) {
  return request.post('/user/delete', params)
}


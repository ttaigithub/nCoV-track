const data = [
  {
    id: 1,
    parentId: 0
  },
  {
    id: 2,
    parentId: 1
  },
  {
    id: 3,
    parentId: 1
  },
  {
    id: 4,
    parentId: 3
  }
]

const createTree = (array, parentId) => {
  const arrayCopy = Object.assign([], array)
  const res = []
  // for (let i = array.length - 1; i >= 0; i--) {
  //   const item = array[i]
  //   if (item.parentId === parentId) {
  //     arrayCopy.splice(i, 1)
  //     res.unshift(item)
  //   }
  // }
  for (let i = 0; i < array.length - 1; i++) {
    const item = array[i]
    if (item.parentId === parentId) {
      res.push(item)
      arrayCopy.splice(i, 1)
    }
  }
  // console.log(res.map(item => item.id))
  // console.log(arrayCopy.map(item => item.id))

  for (let i = 0; i < res.length; i++) {
    const item = res[i]
    item.children = createTree(arrayCopy, item.id)
  }
  return res
}

createTree(data, 0)

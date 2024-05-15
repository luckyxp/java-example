function repeat(arr) {

    for (var i = 0; i < arr.length; i++) {
        for (var j = i + 1; j < arr.length; j++) {//第一次是拿第一个数据和剩余的n-1个数据判断是否相等、第二次是拿第二个数据和剩余的n-1个数据相比
            if (arr[i] == arr[j]) {

                var del = j;

                arr.splice(del, 1);//找到了该数据的下标就删除了
            }
        }
    }
    return arr;
}

const arr = ['a','a','a','s','h','g','a','y','t','a'];
console.log(repeat(arr));
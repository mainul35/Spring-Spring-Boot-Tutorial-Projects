function sortTable(column_ids){
    var table = $('table');

    $(column_ids)
        .wrapInner('<span title="sort this column"/>')
        .each(function(){

            var th = $(this),
                thIndex = th.index(),
                inverse = false;

            th.click(function(){

                table.find('td').filter(function(){

                    return $(this).index() === thIndex;

                }).sortElements(function(a, b){

                    return $.text([a]) > $.text([b]) ?
                        inverse ? -1 : 1
                        : inverse ? 1 : -1;

                }, function(){

                    // parentNode is the element we want to move
                    return this.parentNode;

                });

                inverse = !inverse;

            });

        });

}

function searchTable(col_num){
    $("#search").on("keyup", function() {
        var value = $(this).val();

        $("table tr").each(function(index) {
            if (index !== 0) {

                $row = $(this);

                var id = $row.find("td:nth-child(n+"+col_num+")").text().toLowerCase();

                if (id.indexOf(value.toLowerCase()) !== 0) {
                    $row.hide();
                }
                else {
                    $row.show();
                }
            }
        });
    });
}

function paginate_table(table_selector) {
    $(table_selector).datatable({
        pageSize: 2,
        sort: [false, false, false],
        filters: [false, false, false],
        filterText: 'Type to filter... ',
        onChange: function(old_page, new_page){
            console.log('changed from ' + old_page + ' to ' + new_page);
        }
    }) ;
}
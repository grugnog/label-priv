$(document).ready( function () {
    var term = $("#term").val();
    $('#labelTable').dataTable({
        searching: false,
        processing: true,
        serverSide: true,
        sPaginationType: "full_numbers",
        sDom: "<'row'<'col-md-6'i><'col-md-6'<'pull-right'p>>r>t<'row'<'col-md-6'><'col-md-6'>>",
        oLanguage: {
            oPaginate: {
                sPrevious: "<",
                sNext: ">",
                sFirst: "<<",
                sLast: ">>"
            } ,
            sEmptyTable:"No results found. Please update your query.",
            sInfo: "Showing _START_ - _END_ of _TOTAL_ labels"
        },
        ajax: {
            url: "/LABEL/search/textSearch",
            data: {term:term},
            type: "POST"
        },
        columns: [
            {
                data: "labelDetails",
                "render" : function(data, type, r, meta) {
                    var api = new $.fn.dataTable.Api( meta.settings );
                    var currentPage = api.page();
                    var mterm = term ;
                    if(mterm.indexOf('#') >= 0) {
                        mterm = mterm.replace('#', "%23");
                    }
                    var content = '<a name="labelDetailsLink" class="labelDetails" href="details?id='+data.id+'&term='+mterm+'&page='+currentPage+'">'+data.title+'</a><p name="labelDescription" class="labelDescription">'+data.description+'</p>';
                    return content;
                }
            }
        ],
        "fnDrawCallback": function(oSettings) {
            if ($('#labelTable tbody tr td').html() == "No results found. Please update your query.") {
                $('.dataTables_paginate').hide();
            }
        }
    });


   //To handle advance search
    $("#termText").autocomplete({
        autoFocus: true,
        source: function (request, response) {
            if(request.term.startsWith("#") && request.term.length > 1 && request.term.indexOf(':') == -1){
                $.ajax({
                    dataType:"JSON",
                    type:"GET",
                    data:{term:request.term},
                    url:"/LABEL/search/autocomplete",
                    success: function(data){
                        response(data);
                    },
                    error: function(xhr, status, error){

                    }
                });
            }
        },
        select: function(event, ui) {
            $("#termText").val("#"+ui.item.label+":");
            return false;
        }
    });
} );

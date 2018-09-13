<script type="text/javascript">
<!--
$(document).ready(function(){
    var index = $('#processor');
    var sort = parseInt(index.val());
    $('#processorSelect > option').eq(sort).attr('selected','selected');
});

function processorOrder(){
    var index = $("#processorSelect option").index($("#processorSelect option:selected"));
    $('#processor').val(index);
    return true;
}

function deletesort(){
    $('#preference').attr('action','Preference.jwma?deleteMailId').submit();
}

function addsort(){
    $('#preference').attr('action','Preference.jwma?addMailId').submit();
}

function updateList(){}
// -->
</script>
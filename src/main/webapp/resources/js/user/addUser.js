/**
 * Created by lopes_f on 1/23/2015.
 */

var teacherTypeSelectValue = "teacher";
var gradeListSelector = "#grades";
var userTypesSelector = "#userTypes";

$(document).ready(function() {

    registerListeners();

    var registerListeners = function () {

        $(gradeListSelector).change(function() {
            if ($(this).attr("value") == teacherTypeSelectValue) {
                $(userTypesSelector).hide();
            } else {
                $(userTypesSelector).show();
            }
        });
    }
});

function parseDocument(documentStr) {
    console.debug("[core-library] Parsing document: ");
    console.debug(documentStr);
    var jsObj = JSON.parse(documentStr);
    console.debug("[core-library] Document Parsed: " + jsObj);
    jsObj.parsed = true;
    return JSON.stringify(jsObj);
}

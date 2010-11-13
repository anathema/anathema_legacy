#!/usr/bin/python

import sys, os
import unicodedata, re


###
### pdf-miner requirements
###

from pdfminer.pdfparser import PDFParser, PDFDocument, PDFNoOutlines
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import PDFPageAggregator
from pdfminer.layout import LAParams, LTTextBox, LTTextLine, LTFigure, LTImage

def with_pdf(function):
    def pdf_function(pdf_doc, password='', *args, **kwargs):
        result = None
        try:
            # open the pdf file
            fp = open(pdf_doc, 'rb')
            # create a parser object associated with the file object
            parser = PDFParser(fp)
            # create a PDFDocument object that stores the document structure
            doc = PDFDocument()
            # connect the parser and document objects
            parser.set_document(doc)
            doc.set_parser(parser)
            # supply the password for initialization
            doc.initialize(password)

            if doc.is_extractable:
                # apply the function and return the result
                result = function(doc, *args, **kwargs)

            # close the pdf file
            fp.close()
        except IOError:
            # the file doesn't exist or similar problem
            pass
        return result
    pdf_function.func_name = function.func_name
    pdf_function.func_doc = function.func_doc
    return pdf_function


### 
### Table of Contents
### 

@with_pdf
def get_toc(doc):
    """Get the table of contents (toc) data from a PDF"""
    toc = []
    try:
        outlines = doc.get_outlines()
        for (level,title,dest,a,se) in outlines:
            toc.append( (level, title) )
    except PDFNoOutlines:
        pass
    return toc


###
### Extracting Text
###

def to_bytestring (s, enc='utf-8'):
    """Convert the given unicode string to a bytestring, using the standard encoding,
    unless it's already a bytestring"""
    if s:
        if isinstance(s, str):
            return s
        else:
            return s.encode(enc)

def update_page_text_hash (h, lt_obj, pct=0.2):
    """Use the bbox x0,x1 values within pct% to produce lists of associated text within the hash"""
    x0 = lt_obj.bbox[0]
    x1 = lt_obj.bbox[2]
    key_found = False
    for k, v in h.items():
        hash_x0 = k[0]
        if x0 >= (hash_x0 * (1.0-pct)) and (hash_x0 * (1.0+pct)) >= x0:
            hash_x1 = k[1]
            if x1 >= (hash_x1 * (1.0-pct)) and (hash_x1 * (1.0+pct)) >= x1:
                # the text inside this LT* object was positioned at the same
                # width as a prior series of text, so it belongs together
                key_found = True
                v.append(to_bytestring(lt_obj.get_text()))
                h[k] = v
    if not key_found:
        # the text, based on width, is a new series,
        # so it gets its own series (entry in the hash)
        h[(x0,x1)] = [to_bytestring(lt_obj.get_text())]
    return h

def parse_lt_text (lt_objs, page_number, text=[]):
    """Iterate through the list of LT* objects and capture the text contained in each"""
    text_content = []

    page_text = {} # k=(x0, x1) of the bbox, v=list of text strings within that bbox width (physical column)
    for lt_obj in lt_objs:
        if isinstance(lt_obj, LTTextBox) or isinstance(lt_obj, LTTextLine):
            # text, so arrange is logically based on its column width
            page_text = update_page_text_hash(page_text, lt_obj)
        elif isinstance(lt_obj, LTFigure):
            # LTFigure objects are containers for other LT* objects, so recurse through the children
            text_content.append(parse_lt_text(lt_obj.objs, page_number, text_content))

    for k, v in sorted([(key,value) for (key,value) in page_text.items()]):
        # sort the page_text hash by the keys (x0,x1 values of the bbox),
        # which produces a top-down, left-to-right sequence of related columns
        text_content.append('\n'.join(v))

    return '\n'.join(text_content)


###
### Processing Pages
###

@with_pdf
def get_pages (doc):
    """Get the pages from a PDF, extracting images and text."""
    rsrcmgr = PDFResourceManager()
    laparams = LAParams()
    device = PDFPageAggregator(rsrcmgr, laparams=laparams)
    interpreter = PDFPageInterpreter(rsrcmgr, device)

    text_content = []
    for i, page in enumerate(doc.get_pages()):
        print 'Processing page %i' % (i + 1)
        interpreter.process_page(page)
        # receive the LTPage object for this page
        layout = device.get_result()
        # layout is an LTPage object which may contain child objects like LTTextBox, LTFigure, LTImage, etc.
        text_content.append(parse_lt_text(layout.objs, (i+1)))

    return text_content


###
### Processing Exalted books
###

def remove_ligatures(s):
    ligatures = [unichr(i) for i in range(0xFB00, 0xFB06 + 1)]
    for c in ligatures:
        s = s.replace(c + ' ', c)
    return unicodedata.normalize('NFKD', s)

spaces = re.compile('[ ]+', re.UNICODE)
page_number_top = re.compile('\A\d+\s+', re.UNICODE)
page_number_bottom = re.compile('(\A|\s+)\d+\Z', re.UNICODE)
drivethru_order_number = re.compile('\A.*?\(order #\d+\)\s+', re.UNICODE)
drivethru_ip = re.compile('\s+\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\Z', re.UNICODE)
def clean_page(s):
    s = s.decode('utf8')
    s = s.strip()
    s = remove_ligatures(s)
    s = drivethru_ip.sub('', s)
    s = drivethru_order_number.sub('', s)
    s = page_number_top.sub('', s)
    s = page_number_bottom.sub('', s)
    s = spaces.sub(' ', s)
    s = s.encode('utf8')
    return s



@with_pdf
def get_clean_pages(doc):
    """Get the pages from a PDF, extracting images and text."""
    rsrcmgr = PDFResourceManager()
    laparams = LAParams()
    device = PDFPageAggregator(rsrcmgr, laparams=laparams)
    interpreter = PDFPageInterpreter(rsrcmgr, device)

    text_content = []
    for i, page in enumerate(doc.get_pages()):
        print 'Processing page %i' % (i + 1)
        interpreter.process_page(page)
        # receive the LTPage object for this page
        layout = device.get_result()
        # layout is an LTPage object which may contain child objects like LTTextBox, LTFigure, LTImage, etc.
        text_content.append(clean_page(parse_lt_text(layout.objs, (i+1))))

    return text_content

charm = re.compile('^([A-Z][^a-z\n]*)\n\s*(C\s*o\s*s\s*t\s*:.+?)' +
                   '(?:(?=^[A-Z][^a-z\n]*\n\s*C\s*o\s*s\s*t\s*:)|\Z)',
                   re.MULTILINE | re.DOTALL | re.UNICODE)
def extract_charms(pages, page_offset=0):
    charms = []
    charmHanging = False
    for num, page in enumerate(pages):
        first = True
        for m in charm.finditer(page):
            if first and charmHanging and m.start() != 0:
                charms[-1][2] += '\n' + page[:m.start()]
                charmHanging = False
            charms.append([num + page_offset + 1, m.group(1), m.group(2)])
            first = False
            if m.end() == len(page): charmHanging = True
        if first: charmHanging = False
    return charms

def main(argv):
    if len(argv) == 3:
        password = argv[1]
        pdf_doc, outfile = argv[0], argv[2]
    else:
        password = ''
        pdf_doc, outfile = argv[0], argv[1]
    if outfile == '-':
        of = sys.stdout
    else:
        of = open(outfile, 'w')
    pages = get_clean_pages(pdf_doc, password)
    charms = extract_charms(pages)
    of.write('\n'.join(['=== p. %i ===\n%s\n%s' % tuple(c) for c in charms]))
    if outfile != '-':
        of.close()

if __name__ == '__main__':
    argv = sys.argv[1:]
    if len(argv) not in (2, 3):
        exit('Please pass the path of a PDF with charms to extract, (if necessary) the password to the document, and a file in which to store the resulting charm list.')
    main(argv)

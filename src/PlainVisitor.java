
public class PlainVisitor implements MDElementVisitor {
	
	public void visit(Header header)
	{
		if(header.level == 0)
		{
			Document.HtmlStr = Document.HtmlStr.substring(0,header.startIndex) +"<head>\n"
					+ Document.HtmlStr.substring(header.startIndex)+"</head>"+"\n";
		}
		else 
		{
			String head = "<h"+ Integer.toString(header.level)+">";
			String end = "</h"+ Integer.toString(header.level)+">";
			Document.HtmlStr = Document.HtmlStr.substring(0,header.startIndex) + head+"\n"
					+ Document.HtmlStr.substring(header.startIndex)+end+"\n";
		}
	
			
	}
	public void visit(PlainText plaintext)
	{

		Document.HtmlStr +=plaintext.text ;
	}
	public void visit(StyleText styletext)
    {
       if(styletext.type==5)
       {
             Document.HtmlStr= Document.HtmlStr.substring(0, styletext.startIndex) + "<em>"
                   + Document.HtmlStr.substring(styletext.startIndex) + "</em>";
       }
       else if(styletext.type==6)
       {
             Document.HtmlStr= Document.HtmlStr.substring(0, styletext.startIndex) + "<strong>"
                   + Document.HtmlStr.substring(styletext.startIndex) + "</strong>";
       }
       else if(styletext.type==7)
       {
          Document.HtmlStr= Document.HtmlStr.substring(0, styletext.startIndex) + "<em><strong>"
                   + Document.HtmlStr.substring(styletext.startIndex) + "</strong></em>";
       }
    }
	public void visit(Document document)
	{

	}
	public void visit(Node node)
	{
		
	}
    
	public void visit(Token token) {

	}
	public void visit(QuotedBlock quotedBlock)
	{
		Document.HtmlStr = Document.HtmlStr.substring(0,quotedBlock.startIndex) +"<blockquote>\n"
				+ Document.HtmlStr.substring(quotedBlock.startIndex)+"</blockquote>"+"\n";
	}
	public void visit(Horizonta horizonta)
	{
		Document.HtmlStr = Document.HtmlStr.substring(0,horizonta.startIndex)+"\n<hr>\n";
	}
	@Override
	public void visit(CodeBlock codeBlock) {
		int i =0;
		
		for(i =0;i<codeBlock.contents.length();i++)
		{
			if(codeBlock.contents.charAt(i)=='&')
				codeBlock.contents = codeBlock.contents.substring(0,i)+"&amp;"+ codeBlock.contents.substring(i+1);
			if(codeBlock.contents.charAt(i)=='<')
				codeBlock.contents = codeBlock.contents.substring(0,i)+"&lt;"+ codeBlock.contents.substring(i+1);
			if(codeBlock.contents.charAt(i)=='>')
				codeBlock.contents = codeBlock.contents.substring(0,i)+"&gt;"+ codeBlock.contents.substring(i+1);
		}
		
		Document.HtmlStr = Document.HtmlStr.substring(0,codeBlock.startIndex)  +"<pre><code>\n"
				+ codeBlock.contents+"</code></pre>\n";
		
	}
    public void visit(ItemList itemlist){
		      if(itemlist.IsOrdered==1){
		         
		         Document.HtmlStr = Document.HtmlStr.substring(0,itemlist.startIndex)
		        		 +"<ol>\n"+Document.HtmlStr.substring(itemlist.startIndex)+"</ol>\n";
		      }
		      else
		         Document.HtmlStr = Document.HtmlStr.substring(0,itemlist.startIndex)
		         +"<ul>\n"+Document.HtmlStr.substring(itemlist.startIndex)+"</ul>\n";
		      
		   }
	@Override
	public void visit(Link link) {
		Document.HtmlStr = Document.HtmlStr.substring(0,link.startIndex)+link.linkElements;
				
		
	}
	@Override
	public void visit(Image image) {
		// TODO Auto-generated method stub
		Document.HtmlStr = Document.HtmlStr.substring(0,image.startIndex)+image.linkElements;
	}
}
